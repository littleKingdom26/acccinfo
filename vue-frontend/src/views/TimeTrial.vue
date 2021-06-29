<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                TIME TRIAL
            </h1>
            <h3 class="subTitle">
                FINAL LAP Time Trial Server Lap Time Record
            </h3>
            <hr class="yellow" />
            <div class="tabs selections">
                <b-dropdown
                    text="CAR"
                    no-flip
                    no-caret
                    variant=""
                    :class="{ active: carClass_selection != 'CAR' }"
                >
                    <template #button-content>
                        <span>{{ carClass_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(carClass, carClassIdx) in carClasses"
                        :key="carClassIdx"
                        href="#"
                        @click.stop.prevent="onClickCarClassDropdown(carClass)"
                        >{{ carClass.value }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="EVENT"
                    no-flip
                    no-caret
                    variant=""
                    class="doubleSize listType"
                    :disabled="events.length == 1"
                    :class="{ active: event_selection != 'EVENT' }"
                >
                    <template #button-content>
                        <span>{{ event_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="event in events"
                        :key="event.sessionId"
                        href="#"
                        @click.stop.prevent="onClickEventDropdown(event)"
                    >
                        {{
                            event.startDt == "ALL"
                                ? "ALL"
                                : `${event.startDt} - ${
                                      event.endDt ? event.endDt : "In Progress"
                                  }`
                        }}
                    </b-dropdown-item>
                </b-dropdown>
                <b-dropdown
                    text="CIRCUIT"
                    no-flip
                    no-caret
                    variant=""
                    :disabled="track_disabled"
                    class="doubleSize listType"
                    :class="{ active: track_selection != 'CIRCUIT' }"
                >
                    <template #button-content>
                        <span>{{ track_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="track in tracks"
                        :key="`${track_selection}_${track.seq}`"
                        href="#"
                        @click.stop.prevent="onClickTrackDropdown(track)"
                        >{{ track.trackName }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown style="visibility:hidden;"> </b-dropdown>
                <div class="nameFilterWrap">
                    <b-form-input
                        v-model="nameFilter"
                        placeholder="SEARCH"
                        :style="nameFilterStyle"
                    ></b-form-input>
                </div>
            </div>

            <div class="tabs list">
                <div
                    v-if="results.length || doneFetchResults"
                    class="session notice Staatliches text-center mb-5"
                >
                    <div class="row header">
                        <div class="count">NO</div>
                        <div class="nickname">NICKNAME</div>
                        <div class="carName">CAR</div>
                        <div class="bestlap">BEST LAP</div>
                        <div
                            class="gap"
                            @click="_setGapCriteria(results[0].bestLap)"
                        >
                            GAP
                        </div>
                        <div class="bestSec">BEST S1</div>
                        <div class="bestSec">BEST S2</div>
                        <div class="bestSec">BEST S3</div>
                        <div class="potential">POTENTIAL</div>
                    </div>

                    <div
                        class="row Inter"
                        v-for="(row, rowIdx) in filteredResults"
                        :key="`${carClass_selection}_${rowIdx}`"
                        :data-seq="row.seq"
                    >
                        <div class="count" :class="{ top: row.rank <= 3 }">
                            <span>{{ row.rank }}</span>
                        </div>
                        <div class="nickname" @click="onClickPlayerDetail(row)">
                            {{ _getFullName(row) }}
                        </div>
                        <div class="carName">{{ row.carName }}</div>
                        <div class="bestlap">{{ row.bestLap | secToMin }}</div>
                        <div class="gap" @click="_setGapCriteria(row.bestLap)">
                            {{ _getGap(row.bestLap) | secToMinForGap }}
                        </div>
                        <div class="bestSec">{{ row.sector1 | secToMin }}</div>
                        <div class="bestSec">{{ row.sector2 | secToMin }}</div>
                        <div class="bestSec">{{ row.sector3 | secToMin }}</div>
                        <div class="potential">
                            {{
                                (row.sector1 + row.sector2 + row.sector3)
                                    | secToMin
                            }}
                        </div>
                    </div>
                    <div class="row Inter" v-if="!filteredResults.length">
                        <div class="nickname">
                            결과 없습니다.
                        </div>
                    </div>
                </div>
                <div v-else class="session notice Staatliches text-center mb-5">
                    <div class="row header empty">
                        <div class="count Staatliches">
                            <h1>{{ initMessage }}</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="tabs lastBtnWrap Staatliches">
                <b-row>
                    <b-col>
                        <b-button class="lastBtn" to="/notice/64"
                            ><span>TIME TRIAL SERVER GUIDE</span>
                            <mdiChevronRightCircle color="#ffffff"
                        /></b-button>
                    </b-col>
                </b-row>
            </div>
        </div>

        <div class="text-center mb-5">
            <b-button variant="link" class="logo" to="/"></b-button>
        </div>

        <PlayerDetail
            :showDetail="showDetail"
            :playerDetail="playerDetail"
            :playerTTscore="playerTTscore"
            :carClass="carClass_selection"
            :_setShowDetail="_setShowDetail"
        />
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header";
import PlayerDetail from "@/components/PlayerDetail";
import mdiChevronRightCircle from "vue-material-design-icons/ChevronRightCircle.vue";
import mdiChevronDownCircle from "vue-material-design-icons/ChevronDownCircle.vue";
import moment from "moment";

export default {
    name: "Home",
    components: {
        Header,
        PlayerDetail,
        mdiChevronRightCircle,
        mdiChevronDownCircle,
    },
    data() {
        return {
            _initMessage: "CHOOSE YOUR EVENT",
            initMessage: "CHOOSE YOUR EVENT",
            showDetail: false,
            gapCriteria: 0,
            nameFilter: "",
            carClass_selection: "GT3",
            event_selection: "EVENT",
            event_selection_data: {},
            track_selection: "CIRCUIT",
            track_disabled: false,
            carClasses: [],
            events: [],
            tracks: [],
            results: [],
            sliderValue: 0,
            isHideBallast: false,
            playerDetail: [],
            playerTTscore: 0,
            doneFetchResults: false,
        };
    },
    created() {
        this._getCarClasses();
        this._getEvents();
        this._getTracks();
    },
    computed: {
        nameFilterStyle() {
            return {
                visibility: this.results.length == 0 ? "hidden" : "",
            };
        },
        filteredResults() {
            let result = this.results;
            if (this.nameFilter) {
                result = this.results.filter((item) => {
                    let fullname = this._getFullName(item);
                    return (
                        fullname
                            .toLowerCase()
                            .indexOf(this.nameFilter.toLowerCase()) != -1
                    );
                });
            }
            return result;
        },
    },
    filters: {
        secToMin(sec) {
            let secString = String(sec);
            let secs = secString.slice(0, -3);
            let decimals = secString.slice(-3);
            let convertedSec = moment.utc(secs * 1000).format("mm:ss");
            return `${convertedSec}.${decimals}`;
        },
        secToMinForGap(sec) {
            let _makeThreeChar = (string) => {
                if (string) {
                    for (let i = string.length; i < 3; i++) {
                        string = string + "0";
                    }
                    return string;
                }
                return "000";
            };
            let secString = String(sec);
            let isMinus = false;
            if (secString.indexOf("-") != -1) {
                isMinus = true;
                secString = secString.slice(1, secString.length);
            }
            let secs = secString.slice(0, -3);
            let decimals = String(parseInt(secString.slice(-3)) / 1000).split(
                "."
            )[1];
            decimals = _makeThreeChar(decimals);
            let convertedSec = moment.utc(secs * 1000).format("s");
            let frontMark = "+";
            if (isMinus) {
                frontMark = "-";
            }
            let result = `${frontMark}${convertedSec}.${decimals}`;
            return result == "+0.000" ? "" : result;
        },
    },
    methods: {
        _getFullName(player) {
            return player.firstName && player.firstName == "."
                ? player.lastName
                : `${player.firstName} ${player.lastName}`;
        },
        _getCarClasses() {
            this.$axios
                .get("/api/common/carClass", { withCredentials: false })
                .then((data) => {
                    this.carClasses = data.data.data;
                });
        },
        _getEvents() {
            this.$axios
                .get("/api/timeTrial/week", { withCredentials: false })
                .then((data) => {
                    this.events = [
                        { startDt: "ALL", sessionId: 0 },
                        ...data.data.data,
                    ];
                });
        },
        _getTracks() {
            this.$axios
                .get("/api/timeTrial/track", { withCredentials: false })
                .then((data) => {
                    this.tracks = data.data.data;
                });
        },
        _setOrderOnResults() {
            for (let i = 0; i < this.results.length; i++) {
                // ballast 보이기 여부 확인
                if (i == 0) {
                    if (!this.results[i].ballast) {
                        this.isHideBallast = true;
                    } else {
                        this.isHideBallast = false;
                    }
                }
                // rank 없으면, rank 값 추가
                if (this.results[i].rank == undefined) {
                    this.results[i].rank = i + 1;
                }
            }
        },
        _getGtTTResult() {
            this.initMessage = "Loading...";
            this.results = [];
            this.doneFetchResults = false;
            this.$axios
                .get(
                    `/api/timeTrial/week/${this.carClass_selection.toLowerCase()}/${
                        this.event_selection_data.sessionId
                    }`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.results = data.data.data;
                    // console.info("this.results", this.results);
                    if (this.results.length) {
                        this.gapCriteria = this.results[0].bestLap;
                    }
                    this._setOrderOnResults();
                    this.doneFetchResults = true;
                });
        },
        _getPlayerDetail(playerId) {
            this.$axios
                .get(
                    `/api/timeTrial/week/${this.carClass_selection.toLowerCase()}/${playerId}/detail`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.playerDetail = data.data.data;
                    if (this.carClass_selection == "GT3") {
                        this._getPlayerTtScore(playerId);
                    }
                });
        },
        _getPlayerTtScore(playerId) {
            this.$axios
                .get(
                    `/api/timeTrial/week/${this.carClass_selection.toLowerCase()}/${playerId}/ttScore`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.playerTTscore = Math.floor(data.data.data.TTscore);
                });
        },
        _getSeasonAllResult() {
            if (
                this.year_selection == "YEAR" ||
                !this.class_selection_division
            ) {
                return;
            }
            this.doneFetchResults = false;
            this.$axios
                .get(
                    `/api/result/season/${this.year_selection}/${this.class_selection_division}/all`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.results = data.data.data;
                    this._setOrderOnResults();
                    this.doneFetchResults = true;
                });
        },
        _setTrackBySeq(trackSeq) {
            let trackData = this.tracks.filter((item) => {
                return item.seq == trackSeq;
            });
            if (trackData.length) {
                this.track_selection = trackData[0].trackName;
                this.track_selection_data = trackData[0];
            }
        },
        _getGap(bestLap) {
            return bestLap - this.gapCriteria;
        },
        _setGapCriteria(bestLap) {
            this.gapCriteria = bestLap;
        },
        _setShowDetail(val) {
            this.showDetail = val;
        },
        onClickCarClassDropdown(carClass) {
            this.carClass_selection = carClass.value;
            this._getGtTTResult();
        },
        onClickEventDropdown(event) {
            this.event_selection =
                event.startDt == "ALL"
                    ? "ALL"
                    : `${event.startDt} - ${
                          event.endDt ? event.endDt : "In Progress"
                      }`;
            this.event_selection_data = event;

            if (event.startDt == "ALL") {
                this.track_selection = "CIRCUIT";
                this.track_selection_data = {};
                this.track_disabled = false;
                this.results = [];
            } else {
                this._setTrackBySeq(event.trackSeq);
            }

            this._getGtTTResult();
        },
        onClickTrackDropdown(trackData) {
            this.track_selection = trackData.trackName;
            this.track_selection_data = trackData;

            this.event_selection = "ALL";
            this.event_selection_data = {};
        },
        async onClickPlayerDetail(row) {
            await this._getPlayerDetail(row.playerId);
            this.showDetail = true;
        },
    },
};
</script>

<style scoped>
* >>> .yellow {
    color: var(--yellow);
}

.session {
    padding: 2rem 0;
    padding-top: 7rem;
    /* max-width: 1060px; */
    max-width: 1140px;
    min-height: 100vh;
    margin: 0 auto;
    text-align: center;
}
.session.notice {
    padding: 0;
}
.session:first-child {
    padding-top: 0;
}
.session:last-child {
    padding-bottom: 5rem;
}
.session h1.title {
    font-size: 2rem;
}
.session h2.title {
    font-size: 1.5rem;
    color: var(--yellow);
    margin-bottom: 2rem;
}
.session h3.title {
    font-size: 1.2rem;
}
.session h2.title {
    font-size: 1.5rem;
    color: var(--yellow);
    margin-bottom: 2rem;
}
.session .subTitle {
    margin-top: 1rem;
    color: #8a8a8a;
    font-weight: 100;
    font-size: 1rem;
}
.session .row {
    display: flex;
    min-height: 46px;
    margin: 0.5rem 0;
    font-weight: bold;
    font-size: 0.8em;
}
.session .row.header {
    min-height: 50px;
    border-top: 2px solid #fff;
    border-bottom: 2px solid #fff;
    margin: 1rem 0;
    font-size: 1em;
}
.session .row.header.empty {
    background-color: #171717;
    min-height: 400px;
}
.session .row > div {
    align-self: center;
    align-items: center;
    display: flex;
    justify-content: center;
    align-self: stretch;
}
.session .row.header > div {
    font-size: 1.2rem;
}
.session .row.header .count {
    background-color: #4d4d4d;
}
.session .row .count {
    flex: 1 1 0;
    background-color: var(--yellow);
    font-weight: bold;
}
.session .row .count.top {
    position: relative;
    color: #000;
}
.session .row .count.top > span {
    z-index: 1;
}
.session .row .count.top::before {
    content: "";
    background-image: url("/vue_assets/img/star.png");
    background-size: 60%;
    background-repeat: no-repeat;
    background-position: center;
    position: absolute;
    width: 100%;
    height: 100%;
}
.session .row.empty .count {
    background-color: transparent;
    color: var(--yellow);
}
.session .row .title {
    flex: 1 1 0;
    color: #000;
    background-color: #fff;
}
.session .row.header .title {
    color: #8a8a8a;
}
.session .row .nickname,
.session .row .carName,
.session .row .bestlap,
.session .row .gap,
.session .row .bestSec,
.session .row .bestSec,
.session .row .bestSec,
.session .row .potential {
    flex: 1 1 0;
    background-color: #4d4d4d;
    text-transform: uppercase;
}
.session .row .nickname {
    flex: 2 1 0;
    cursor: pointer;
}
.session .row .carName {
    flex: 4 1 0;
}
.session .row.Inter .bestlap {
    color: var(--yellow);
}

hr.yellow {
    max-width: 50px;
    border-width: 3px;
    border-color: var(--yellow);
    margin: 1rem auto;
    border-style: solid;
    opacity: 1;
}

.tabs {
    margin: 5rem 0;
}
.tabs .roundedBtn {
    border-radius: 20px;
    margin: 1rem;
    padding: 0.25rem 6rem;
    border-color: transparent;
    background-color: #fff;
}
.tabs .roundedBtn.active,
.tabs .roundedBtn:hover {
    background-color: var(--yellow);
}
.tabContent {
    max-width: 620px;
    margin: 0 auto;
}
.tabContent .row:first-child {
    border-top: 1px solid #fff;
}
.tabContent .row {
    border-bottom: 1px solid #fff;
}

.schedule .frstColumn {
    padding: 20px;
}
.schedule .frstColumn > img {
    max-width: 60px;
}
.schedule .secColumn {
    background-color: #8a8a8a2b;
    display: flex;
    flex-direction: column;
    align-self: stretch;
    justify-content: center;
}
.schedule .thrColumn {
    color: #8a8a8a;
    padding: 1.5rem;
    display: flex;
    align-self: center;
    flex-direction: column;
}
.schedule .secColumn > span,
.schedule .thrColumn > span {
    align-self: center;
    font-weight: 800;
}

.selections {
    display: flex;
    justify-content: space-between;
}
.selections >>> .b-dropdown {
    flex: 1 1 0;
    margin: 0 0.5em;
}
.selections >>> .b-dropdown.doubleSize {
    flex: 2 1 0;
}
.selections >>> .b-dropdown .dropdown-toggle {
    padding: 0.5em 1em;
    border-radius: 1.5em;
    background-color: #fff;
    color: #8a8a8a;
}

.selections >>> .b-dropdown .dropdown-toggle {
    vertical-align: middle;
}
.selections >>> .b-dropdown .dropdown-toggle > .material-design-icon {
    float: right;
    margin: 0 !important;
}

.selections >>> .b-dropdown.active .dropdown-toggle {
    background-color: var(--yellow);
    color: #fff;
}

.selections >>> .b-dropdown .dropdown-menu {
    background: transparent;
    width: 100%;
    padding: 0;
}
.selections >>> .b-dropdown .dropdown-menu > li {
    background-color: #8a8a8a;
    padding: 0.5em 1em;
    text-align: center;
    border-radius: 1.5em;
    margin: 0.5em 0;
}
.selections >>> .b-dropdown .dropdown-menu,
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item {
    color: #fff;
}
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:active,
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:hover,
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:focus {
    background-color: transparent;
}

.selections >>> .b-dropdown.listType .dropdown-menu {
    background-color: #8a8a8a;
    border-radius: 20px;
    max-height: 60vh;
    overflow: auto;
    margin-top: 0.5em;
}
.selections >>> .b-dropdown.listType .dropdown-menu > li {
    background-color: transparent;
    padding: 0;
}

.nameFilterWrap input {
    border-radius: 1.5em;
    text-align: center;
}

.list {
    min-height: 100vh;
}

.lastBtnWrap {
    max-width: 700px;
    margin: 0 auto;
}
.lastBtnWrap .lastBtn {
    width: 333px;
    height: 62px;
    font-size: 2em;
    background-color: var(--yellow);
    border-radius: 10px;
    line-height: 2;
}
.lastBtnWrap .lastBtn > * {
    vertical-align: middle;
    margin: 0 0.25em;
}

.logo {
    width: 220px;
    height: 52px;
    background-image: url("/vue_assets/logo/final_lap_logo.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    display: inline-block;
    vertical-align: top;
}
</style>
