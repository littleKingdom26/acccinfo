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
                    v-if="results.length"
                    class="session notice Staatliches text-center mb-5"
                >
                    <div class="row header">
                        <div class="count">NO</div>
                        <div class="nickname">NICKNAME</div>
                        <div class="point">POINT</div>
                        <div class="ballast" v-if="!isHideBallast">BALLAST</div>
                    </div>

                    <div
                        class="row Inter"
                        v-for="(row, rowIdx) in filteredResults"
                        :key="rowIdx"
                        :data-seq="row.seq"
                    >
                        <div class="count" :class="{ top: row.rank <= 3 }">
                            <span>{{ row.rank }}</span>
                        </div>
                        <div class="nickname">
                            {{ row.firstName }} {{ row.lastName }}
                        </div>
                        <div class="point">{{ row.point }}</div>
                        <div class="ballast" v-if="!isHideBallast">
                            {{ row.ballast }}
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
                            <h1>CHOOSE YOUR EVENT</h1>
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
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header";
import mdiChevronRightCircle from "vue-material-design-icons/ChevronRightCircle.vue";
import mdiChevronDownCircle from "vue-material-design-icons/ChevronDownCircle.vue";

export default {
    name: "Home",
    components: {
        Header,
        mdiChevronRightCircle,
        mdiChevronDownCircle,
    },
    data() {
        return {
            nameFilter: "",
            carClass_selection: "CAR",
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
                    let fullname = `${item.firstName} ${item.lastName}`;
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
    methods: {
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
        _getSeasonAllResult() {
            if (
                this.year_selection == "YEAR" ||
                !this.class_selection_division
            ) {
                return;
            }
            this.$axios
                .get(
                    `/api/result/season/${this.year_selection}/${this.class_selection_division}/all`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.results = data.data.data;
                    this._setOrderOnResults();
                });
        },
        onClickCarClassDropdown(carClass) {
            this.carClass_selection = carClass.value;
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
        onClickTrackDropdown(trackData) {
            this.track_selection = trackData.trackName;
            this.track_selection_data = trackData;

            this.event_selection = "ALL";
            this.event_selection_data = {};
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
    cursor: pointer;
    font-weight: bold;
}
.session .row.header {
    min-height: 50px;
    border-top: 2px solid #fff;
    border-bottom: 2px solid #fff;
    margin: 1rem 0;
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
    flex: 10 1 0;
    color: #000;
    background-color: #fff;
}
.session .row.header .title {
    color: #8a8a8a;
}
.session .row .nickname,
.session .row .ballast,
.session .row .point {
    flex: 10 1 0;
    background-color: #4d4d4d;
    text-transform: uppercase;
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
    font-size: 1.5em;
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
