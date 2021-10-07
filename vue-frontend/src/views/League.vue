<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                LEAGUE CALENDAR
            </h1>
            <h3 class="subTitle">
                ACC KOREA League Calendar
            </h3>
            <hr class="yellow" />
        </div>

        <div class="session notice Staatliches text-center mb-5">
            <iframe
                src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23616161&amp;ctz=Asia%2FSeoul&amp;src=ZDVwdDN0NTBrOHRjdTRwbzNtcGpkaW0zYWtAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ&amp;color=%23F4511E&amp;showTitle=0&amp;showCalendars=0&amp;showPrint=0&amp;showNav=1"
                style="border-width:0; max-width: 1140px;"
                width="90%"
                height="600"
                frameborder="0"
                scrolling="no"
            ></iframe>
        </div>

        <div class="session mb-5" id="league_information">
            <h1 class="title">
                LEAGUE INFORMATION
            </h1>
            <h3 class="subTitle">
                FINAL LAP League Information
            </h3>
            <hr class="yellow" />

            <div class="tabs" v-if="posterType.length">
                <b-button
                    v-for="(type, typeIdx) in posterType"
                    :key="typeIdx"
                    class="roundedBtn"
                    :class="{ active: type.key == slideType }"
                    variant="outline-secondary"
                    @click="slideType = type.key"
                    >{{ type.value }}</b-button
                >
            </div>
            <Slider
                v-if="leagueSlideList.length"
                animation="fade"
                :autoplay="false"
                v-model="sliderValue"
                height="auto"
                :stopOnHover="true"
            >
                <SliderItem
                    v-for="(i, index) in leagueSlideList"
                    :key="index"
                    :style="slideStyle"
                    class="leagueCarousel"
                >
                    <img :src="i.imgPath" />
                </SliderItem>
            </Slider>

            <div class="tabs lastBtnWrap Staatliches">
                <b-row class='flex-wrap flex-lg-nowrap'>
                    <b-col>
                        <b-button class="lastBtn" to="/notice/131"
                            ><span>JOIN OUR LEAGUE</span>
                            <mdiChevronRightCircle color="#ffffff"
                        /></b-button>
                    </b-col>
                    <b-col>
                        <b-button class="lastBtn" to="/notice/93"
                            ><span>PENALTY RULES</span>
                            <mdiChevronRightCircle color="#ffffff"
                        /></b-button>
                    </b-col>
                </b-row>
                <b-row class='flex-wrap flex-lg-nowrap mt-4'>
                    <b-col>
                        <b-button class="lastBtn" to="/notice/64"
                            ><span>RACE MANNER</span>
                            <mdiChevronRightCircle color="#ffffff"
                        /></b-button>
                    </b-col>
                    <b-col>
                        <b-button class="lastBtn" to="/notice/82"
                            ><span>BEGINNER GUIDE</span>
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

import { Slider, SliderItem } from "vue-easy-slider";

export default {
    name: "Home",
    components: {
        Header,
        Slider,
        SliderItem,
        mdiChevronRightCircle,
    },
    data() {
        return {
            slideStyle: {
                backgroundPosition: "center",
                backgroundSize: "contain",
                backgroundRepeat: "no-repeat",
                left: "0",
                right: "0",
                margin: "0 auto",
            },
            leagueSlideList: [],
            posterType: [],
            slideType: "sub", // sub : 수요, main : 일요
            sliderValue: 0,
        };
    },
    created() {
        this._getContent();
        this._getPosterType();
    },
    watch: {
        slideType() {
            this._getContent();
        },
    },
    methods: {
        _getContent() {
            this.$axios
                .get(`/api/main/poster/${this.slideType}`, {
                    withCredentials: false,
                })
                .then((data) => {
                    this.leagueSlideList = data.data.data;
                    // console.info("this.leagueSlideList", this.leagueSlideList);
                });
        },
        _getPosterType() {
            this.$axios
                .get(`/api/common/posterType`, {
                    withCredentials: false,
                })
                .then((data) => {
                    this.posterType = data.data.data.sort(function(a, b) {
                        return a.key > b.key ? -1 : a.key > b.key ? 1 : 0;
                    });
                    // console.info("this.posterType", this.posterType);
                });
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
@media (max-width: 980px) {
    .session {
        padding-top: 9rem;
    }
}
@media (max-width: 768px) {
}
@media (max-width: 536px) {
    .session {
        padding-top: 12rem;
    }
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
    font-family: Staatliches, "Jeoju Gthic", Arial, sans-serif;
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
.row{
    margin: 0;
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

.lastBtnWrap {
    max-width: 700px;
    margin: 0 auto;
    margin-top: 3em;
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

* >>> .slider-item {
    position: inherit !important;
}
* >>> .slider-item img{
    max-width: 90%;
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
