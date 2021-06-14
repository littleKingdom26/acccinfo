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
                        @click.stop.prevent="class_selection = className"
                        >{{ className }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="SEASON"
                    no-caret
                    variant=""
                    class="season"
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
                        @click.stop.prevent="season_selection = season"
                        >{{ season }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="ROUND"
                    no-caret
                    variant=""
                    :class="{ active: round_selection != 'ROUND' }"
                >
                    <template #button-content>
                        <span>{{ round_selection }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="(round, roundIdx) in rounds"
                        :key="roundIdx"
                        href="#"
                        @click.stop.prevent="round_selection = round"
                        >{{ round }}</b-dropdown-item
                    >
                </b-dropdown>
                <b-dropdown
                    text="ROUND"
                    no-caret
                    variant=""
                    class=""
                    style="visibility:hidden;"
                >
                    <template #button-content>
                        <span>Search</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <!-- <b-dropdown-item href="#">Action</b-dropdown-item>
                    <b-dropdown-item href="#">Another action</b-dropdown-item>
                    <b-dropdown-item href="#"
                        >Something else here</b-dropdown-item
                    > -->
                </b-dropdown>
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
            year_selection: "YEAR",
            class_selection: "CLASS",
            season_selection: "SEASON",
            round_selection: "ROUND",
            years: ["로딩중..."],
            classes: ["PRO", "MASTER", "ONE MAKE"],
            seasons: ["ALL", "SEASON1", "SEASON2", "SEASON3"],
            rounds: ["ALL", "ROUND1", "ROUND2", "ROUND3"],
            leagueSlideList: [
                {
                    style: {
                        backgroundImage:
                            'url("/vue_assets/img/ps4yxbox_3109823b.jpeg")',
                        bakcgroundPosition: "center",
                        backgroundSize: "contain",
                        backgroundRepeat: "no-repeat",
                        maxWidth: "500px",
                        left: "0",
                        right: "0",
                        margin: "0 auto",
                    },
                    title: `<span><span class="yellow">24 HOURS</span> OPEN</span><br /><span>TIMETRIAL SERVER</span>`,
                    iconClass: "trophy",
                    buttonName: "SEASON CHAMPION",
                },
                {
                    style: {
                        backgroundImage:
                            'url("/vue_assets/img/ps4yxbox_3109823b.jpeg")',
                        bakcgroundPosition: "center",
                        backgroundSize: "contain",
                        backgroundRepeat: "no-repeat",
                        maxWidth: "500px",
                        left: "0",
                        right: "0",
                        margin: "0 auto",
                    },
                    title: `<span><span class="yellow">FINAL LAP</span></span><br /><span>JOIN OUR LEAGUE</span>`,
                    iconClass: "trophy car",
                    buttonName: "REGISTER NOW",
                },
            ],
            sliderValue: 0,
        };
    },
    created() {
        this._getContent();
    },
    methods: {
        _getContent() {
            this.$axios
                .get("/api/result/year", { withCredentials: false })
                .then((data) => {
                    this.years = data.data.data;
                });
        },
        changeIndex(index) {
            this.sliderValue = index;
        },
        onClickYear(year) {
            if (typeof year == "string" && year.indexOf("로딩") != -1) {
                return;
            }
            this.year_selection = year;
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
    flex: 4 1 0;
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
