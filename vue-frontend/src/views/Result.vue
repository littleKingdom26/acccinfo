<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                RESULT
            </h1>
            <h3 class="subTitle">
                FINAL LAP League Result
            </h3>
            <hr class="yellow" />
            <div class="tabs selections">
                <b-dropdown
                    text="YEAR"
                    no-flip
                    no-caret
                    variant=""
                    :class="{ active: year_selection != 'YEAR' }"
                >
                    <template #button-content>
                        <span>{{ year_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(year, yearIdx) in years"
                        :key="yearIdx"
                        href="#"
                        @click.stop.prevent="onClickYear(year)"
                        >{{ year }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="CLASS"
                    no-flip
                    no-caret
                    variant=""
                    :class="{ active: class_selection != 'CLASS' }"
                >
                    <template #button-content>
                        <span>{{ class_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(className, classNameIdx) in classes"
                        :key="classNameIdx"
                        href="#"
                        @click.stop.prevent="onClickClassDropdown(className)"
                        >{{ className.name }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="SEASON"
                    no-flip
                    no-caret
                    variant=""
                    class="season"
                    :disabled="seasons.length == 1"
                    :class="{ active: season_selection != 'SEASON' }"
                >
                    <template #button-content>
                        <span>{{ season_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(season, seasonIdx) in seasons"
                        :key="seasonIdx"
                        href="#"
                        @click.stop.prevent="onClickSeasonDropdown(season)"
                        >{{ season.seasonName }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="ROUND"
                    no-flip
                    no-caret
                    variant=""
                    :disabled="
                        season_selection == 'SEASON' ||
                            (season_selection == 'ALL' &&
                                round_selection == 'ALL')
                    "
                    :class="{ active: round_selection != 'ROUND' }"
                >
                    <template #button-content>
                        <span>{{ round_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(round, roundIdx) in rounds"
                        :key="`${season_selection}_${roundIdx}`"
                        href="#"
                        @click.stop.prevent="onClickRoundDropdown(round)"
                        >{{ round.name }}</b-dropdown-item
                    >
                </b-dropdown>

                <div class="nameFilterWrap">
                    <b-form-input
                        v-model="nameFilter"
                        placeholder="SEARCH"
                        :style="nameFilterStyle"
                    ></b-form-input>
                </div>
                <!-- <b-dropdown
                    text="ROUND"
                    no-flip
                    no-caret
                    variant=""
                    class=""
                    style="visibility:hidden;"
                >
                    <template #button-content>
                        <span>Search</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                </b-dropdown> -->
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
                        <div class="ballast">BALLAST</div>
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
                            {{ row.fistName }} {{ row.lastName }}
                        </div>
                        <div class="point">{{ row.point }}</div>
                        <div class="ballast">{{ row.ballast }}</div>
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
                            <h1>CHOOSE YOUR LEAGUE INFORMATION</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="tabs lastBtnWrap Staatliches">
                <b-row>
                    <b-col>
                        <b-button class="lastBtn"
                            ><span>LEAGUE INFORMATION</span>
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
            year_selection: "YEAR",
            class_selection: "CLASS",
            class_selection_division: "",
            season_selection: "SEASON",
            season_selection_data: {},
            round_selection: "ROUND",
            round_selection_data: {},
            years: ["로딩중..."],
            classes: [
                { name: "PRO", division: "DIVISION_1" },
                { name: "MASTER", division: "DIVISION_2" },
                { name: "ONE MAKE", division: "DIVISION_3" },
            ],
            seasons: [{ seasonName: "ALL" }],
            rounds: [{ name: "ALL" }],
            results: [],
            sliderValue: 0,
        };
    },
    created() {
        this._getYears();
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
                    let fullname = `${item.fistName} ${item.lastName}`;
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
        _getYears() {
            this.$axios
                .get("/api/result/year", { withCredentials: false })
                .then((data) => {
                    this.years = data.data.data;
                });
        },
        _getSessions() {
            if (
                this.year_selection == "YEAR" ||
                !this.class_selection_division
            ) {
                return;
            }
            this.$axios
                .get(
                    `/api/result/season/${this.year_selection}/${this.class_selection_division}`,
                    { withCredentials: false }
                )
                .then((data) => {
                    this.seasons = [
                        { seasonName: "ALL", eventInfoSeq: "ALL" },
                        ...data.data.data,
                    ];
                    this.season_selection = "SEASON";
                    this.season_selection_data = {};
                });
        },
        _getRoundResult() {
            if (!this.season_selection_data.eventInfoSeq) {
                return;
            }
            let getUrl = `/api/result/${this.season_selection_data.eventInfoSeq}`;
            if (this.round_selection_data.round) {
                getUrl += `/${this.round_selection_data.round}`;
            }
            this.$axios.get(getUrl, { withCredentials: false }).then((data) => {
                this.results = data.data.data;

                for (let i = 0; i < this.results.length; i++)
                    if (this.results[i].rank == undefined) {
                        this.results[i].rank = i + 1;
                    }
            });
        },
        _getSeasonAllResult() {
            // if (!this.season_selection_data.eventInfoSeq) {
            //     return;
            // }
            // this.$axios
            //     .get(
            //         `/api/result/${this.season_selection_data.eventInfoSeq}/${this.round_selection_data.round}`,
            //         { withCredentials: false }
            //     )
            //     .then((data) => {
            //         this.results = data.data.data;
            //     });
        },
        _updateRounds(roundNumber) {
            this.rounds = [];
            if (this.season_selection == "ALL") {
                this.rounds.push({
                    name: "ALL",
                });
                this.round_selection = "ALL";
            } else {
                this.rounds.push({
                    name: `ALL`,
                });
                for (let i = 1; i <= roundNumber; i++) {
                    this.rounds.push({
                        name: `ROUND${i}`,
                        round: i,
                    });
                }
            }
        },
        _resetSeasonRound() {
            this._resetRound();
            this._resetSeason();
        },
        _resetRound() {
            this.results = [];
            this.round_selection = "ROUND";
            this.round_selection_data = {};
        },
        _resetSeason() {
            this.results = [];
            this.season_selection = "SEASON";
            this.season_selection_data = {};
        },
        onClickYear(year) {
            if (typeof year == "string" && year.indexOf("로딩") != -1) {
                return;
            }
            this.year_selection = year;
            this._resetSeasonRound();
            if (this.class_selection_division) {
                this._getSessions();
            }
        },
        onClickClassDropdown(className) {
            this.class_selection = className.name;
            this.class_selection_division = className.division;
            this._resetSeasonRound();
            this._getSessions();
        },
        onClickSeasonDropdown(season) {
            this.season_selection = season.seasonName;
            this.season_selection_data = season;
            this._resetRound();
            this._updateRounds(season.round);

            if (season.seasonName == "ALL") {
                this._getSeasonAllResult();
            }
        },
        onClickRoundDropdown(roundData) {
            this.round_selection = roundData.name;
            this.round_selection_data = roundData;
            this._getRoundResult();
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
    flex: 2 1 0;
    margin: 0 0.5em;
}
.selections >>> .b-dropdown.season {
    flex: 5 1 0;
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
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item {
    color: #fff;
}
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:active,
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:hover,
.selections >>> .b-dropdown .dropdown-menu > li > .dropdown-item:focus {
    background-color: transparent;
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
