<template>
    <div class="home">
        <Header />

        <div class="hero">
            <Slider
                animation="fade"
                v-model="sliderValue"
                :duration="5000"
                :speed="1000"
                height="100vh"
                :stopOnHover="true"
            >
                <SliderItem
                    v-for="(i, index) in heroSlideList"
                    :key="index"
                    @click="changeIndex(1)"
                    :style="i.style"
                    class="heroCarousel"
                >
                    <div class="titleWrap Staatliches">
                        <h1 class="title" v-html="i.title"></h1>
                        <div :class="i.iconClass"></div>
                        <div class="seasonChamp">
                            <div class="seasonChampBg" />
                            {{ i.buttonName }}
                        </div>
                    </div>
                </SliderItem>
            </Slider>
        </div>
        <div class="session schedule">
            <h1 class="title">
                LEAGUE SCHEDULE
            </h1>
            <h3 class="subTitle">
                Here Is Our Schedule
            </h3>
            <hr class="yellow" />

            <div class="tabs">
                <b-button
                    class="roundedBtn"
                    :class="{ active: checkLeagueSchedule == 'wed' }"
                    variant="outline-secondary"
                    @click="checkLeagueSchedule = 'wed'"
                    >수요일</b-button
                >
                <b-button
                    class="roundedBtn"
                    :class="{ active: checkLeagueSchedule != 'wed' }"
                    variant="outline-secondary"
                    @click="checkLeagueSchedule = 'sun'"
                    >일요일</b-button
                >
            </div>
            <div v-show="checkLeagueSchedule == 'wed'">
                <h2 class="title">ONE MAKE LEAGUE</h2>
                <div class="tabContent Inter">
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/RED_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>PRACTICE</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>22 : 00 - 22 : 30 (30min)</span></b-col
                        >
                    </b-row>
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/ORANGE_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>QUALIFYING</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>22 : 30 - 22 : 40 (10min)</span></b-col
                        >
                    </b-row>
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/GREEN_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>RACE</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>22 : 40 - 23 : 20 (40min)</span></b-col
                        >
                    </b-row>
                </div>
            </div>
            <div v-show="checkLeagueSchedule != 'wed'">
                <h2 class="title">REGULAR LEAGUE</h2>
                <div class="tabContent Inter">
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/RED_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>PRACTICE</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>[MAS] 22 : 00 - 22 : 30 (30min)</span>
                            <span>[PRO] 22 : 00 - 22 : 30 (30min)</span>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/ORANGE_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>QUALIFYING</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>[MAS] 22 : 30 - 22 : 40 (10min)</span>
                            <span>[PRO] 22 : 30 - 22 : 40 (10min)</span>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col class="frstColumn">
                            <img src="/vue_assets/img/GREEN_LED@2x.png" />
                        </b-col>
                        <b-col cols="3" class="secColumn">
                            <span>RACE</span>
                        </b-col>
                        <b-col cols="7" class="thrColumn">
                            <span>[MAS] 22 : 40 - 23 : 20 (40min)</span>
                            <span>[PRO] 22 : 40 - 23 : 20 (40min)</span>
                        </b-col>
                    </b-row>
                </div>
            </div>
        </div>
        <div class="session champion">
            <h1 class="title">
                SEASON CHAMPION
            </h1>
            <h3 class="subTitle">
                Present Season Championship
            </h3>
            <hr class="yellow" />

            <div
                class="tabs"
                v-if="
                    proChamps.length &&
                        masterChamps.length &&
                        oneMakeChamps.length
                "
            >
                <b-row>
                    <b-col class="tabContentWrap">
                        <h2 class="title">PRO LEAGUE</h2>
                        <div class="tabContent Inter" v-if="proChamps.length">
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${proChamps[0].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ proChamps[0].firstName }}
                                        {{ proChamps[0].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${proChamps[1].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ proChamps[1].firstName }}
                                        {{ proChamps[1].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${proChamps[2].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ proChamps[2].firstName }}
                                        {{ proChamps[2].lastName }}</span
                                    >
                                </div>
                            </div>
                        </div>
                    </b-col>
                    <b-col class="tabContentWrap">
                        <h2 class="title">MASTER LEAGUE</h2>
                        <div
                            class="tabContent Inter"
                            v-if="masterChamps.length"
                        >
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${masterChamps[0].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ masterChamps[0].firstName }}
                                        {{ masterChamps[0].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${masterChamps[1].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ masterChamps[1].firstName }}
                                        {{ masterChamps[1].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${masterChamps[2].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ masterChamps[2].firstName }}
                                        {{ masterChamps[2].lastName }}</span
                                    >
                                </div>
                            </div>
                        </div>
                    </b-col>
                    <b-col class="tabContentWrap">
                        <h2 class="title">ONE MAKE LEAGUE</h2>
                        <div
                            class="tabContent Inter"
                            v-if="oneMakeChamps.length"
                        >
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${oneMakeChamps[0].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ oneMakeChamps[0].firstName }}
                                        {{ oneMakeChamps[0].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${oneMakeChamps[1].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ oneMakeChamps[1].firstName }}
                                        {{ oneMakeChamps[1].lastName }}</span
                                    >
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div
                                        class="profile"
                                        :style="
                                            `backgroundImage: url(${oneMakeChamps[2].steamAvatar})`
                                        "
                                    ></div>
                                    <span class="userName"
                                        >{{ oneMakeChamps[2].firstName }}
                                        {{ oneMakeChamps[2].lastName }}</span
                                    >
                                </div>
                            </div>
                        </div>
                    </b-col>
                </b-row>
            </div>
            <div v-else class="session loading Staatliches text-center mb-5">
                <div class="row header">
                    <div class="count">로딩중...</div>
                </div>
            </div>
        </div>
        <div class="session gallery">
            <h1 class="title">
                GALLERY
            </h1>
            <h3 class="subTitle">
                Sim Racing User Gallery
            </h3>
            <hr class="yellow" />
            <div class="tabs">
                <div class="mg-20 inline">
                    <div class="imgBig imgUp"
                    :style='`background-image: url(${gallery[0].mainFilePath})`'></div>
                    <div class="squareWrap">
                        <div class="square"
                        :style='`background-image: url(${gallery[1].mainFilePath})`'></div>
                        <div class="square"
                        :style='`background-image: url(${gallery[2].mainFilePath})`'></div>
                        <div class="square"
                        :style='`background-image: url(${gallery[3].mainFilePath})`'></div>
                    </div>
                </div>
                <div class="mg-20 inline2">
                    <div class="squareWrap">
                        <div class="square"
                        :style='`background-image: url(${gallery[4].mainFilePath})`'></div>
                        <div class="square"
                        :style='`background-image: url(${gallery[5].mainFilePath})`'></div>
                    </div>
                    <div
                        class="imgBig imgDown"
                        :style='`background-image: url(${gallery[6].mainFilePath})`'
                        :class="{ scaleUp: plusScaleUp }"
                        @mouseover="plusScaleUp = true"
                        @mouseout="plusScaleUp = false"
                        @click='$router.push("/gallery")'
                    >
                        <PlusIcon :size="plusIconSize" />
                    </div>
                </div>
            </div>
        </div>
        <div class="session contact">
            <h1 class="title">
                CONTACT
            </h1>
            <h3 class="subTitle">
                Join Our Social Community
            </h3>
            <hr class="yellow" />

            <div class="tabs">
                <b-row>
                    <b-col
                        class="sns"
                        v-for="(sns, snsIdx) in snsTypes"
                        :key="snsIdx"
                        :class="sns.type"
                        @click="onClickOpenUrl(sns.url)"
                    >
                        <div class="snsLogo"></div>
                        <div class="bottomTitle">{{ sns.title }}</div>
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
import PlusIcon from "vue-material-design-icons/Plus.vue";
import { Slider, SliderItem } from "vue-easy-slider";

export default {
    name: "Home",
    components: {
        Header,
        PlusIcon,
        Slider,
        SliderItem,
    },
    data() {
        return {
            heroSlideList: [
                {
                    style: {
                        backgroundImage:
                            'linear-gradient(rgba(0, 0, 0, 0.527),rgba(0, 0, 0, 0.5)) , url("/vue_assets/img/main_hero/1.jpg")',
                    },
                    title: `<span><span class="yellow">24 HOURS</span> OPEN</span><br /><span>TIMETRIAL SERVER</span>`,
                    iconClass: "trophy",
                    buttonName: "SEASON CHAMPION",
                },
                {
                    style: {
                        backgroundImage:
                            'linear-gradient(rgba(0, 0, 0, 0.527),rgba(0, 0, 0, 0.5)) , url("/vue_assets/img/main_hero/2.jpg")',
                    },
                    title: `<span><span class="yellow">FINAL LAP</span></span><br /><span>JOIN OUR LEAGUE</span>`,
                    iconClass: "trophy car",
                    buttonName: "REGISTER NOW",
                },
            ],
            sliderValue: 0,
            plusScaleUp: false,
            plusIconSize: 60,
            checkLeagueSchedule: "wed",
            snsTypes: [
                {
                    type: "twitch",
                    title: "TWITCH",
                    url: "https://www.twitch.tv/finallap_kr",
                },
                {
                    type: "youtube",
                    title: "YOUTUBE",
                    url:
                        "https://www.youtube.com/channel/UCz51xgfpnJRutRPWa3u4vgA",
                },
                {
                    type: "discord",
                    title: "DISCORD",
                    url: "https://discord.gg/jdNXHfY",
                },
                {
                    type: "opentalk",
                    title: "OPEN TALK",
                    url: "https://open.kakao.com/o/gRZFSL2b",
                },
            ],
            proChamps: [],
            masterChamps: [],
            oneMakeChamps: [],
            gallery: [],
        };
    },
    mounted() {
        this._getLeagueRanker();
        this._getGallery();
    },
    methods: {
        _sortByRank(a, b) {
            return a.rank > b.rank ? 1 : -1;
        },
        _getLeagueRanker(){
            this.$axios
            .get("/api/main/beforeLeagueRanker", { withCredentials: false })
            .then((data) => {
                if (data.data.data) {
                    data.data.data.forEach((data) => {
                        if (data.leagueName.indexOf("Pro") != -1) {
                            this.proChamps = [
                                ...data.beforeLeagueRankerResultList.sort(
                                    this._sortByRank
                                ),
                            ];
                        } else if (data.leagueName.indexOf("Master") != -1) {
                            this.masterChamps = [
                                ...data.beforeLeagueRankerResultList.sort(
                                    this._sortByRank
                                ),
                            ];
                        } else if (data.leagueName.indexOf("OneMake") != -1) {
                            this.oneMakeChamps = [
                                ...data.beforeLeagueRankerResultList.sort(
                                    this._sortByRank
                                ),
                            ];
                        }
                    });
                }
            });
        },
        _getGallery(){
            this.$axios
            .get("/api/main/gallery", { withCredentials: false })
            .then((data) => {
                if (data.data.data) {
                    this.gallery = data.data.data;
                    data.data.data.forEach((data) => {
                        console.info(data.mainFilePath)
                    })
                }
            });
        },
        onClickOpenUrl(link) {
            window.open(link);
        },
        changeIndex(index) {
            this.sliderValue = index;
        },
    },
};
</script>

<style scoped>
* >>> .yellow {
    color: var(--yellow);
}

.hero {
    height: 100vh;
    width: 100%;
    max-width: 1320px;
    max-height: 723px;
    margin: 0 auto;
    position: relative;
    margin-bottom: 5rem;
}
.hero .titleWrap {
    position: absolute;
    text-align: center;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
}
.hero .titleWrap .title {
    margin: 0;
    margin-bottom: 1rem;
    line-height: 1;
    font-size: 3.5rem;
}
.hero .titleWrap .trophy {
    width: 59px;
    height: 59px;
    background-image: url("/vue_assets/img/trophy tab@2x.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    margin: 0 auto;
    transform: translateX(5px);
    margin-bottom: 1rem;
}
.hero .titleWrap .trophy.car {
    background-image: url("/vue_assets/img/car tab@2x.png");
}
.hero .titleWrap .seasonChamp {
    margin: 0 auto;
    border-radius: 20px;
    font-size: 1rem;
    border: 2px solid var(--yellow);
    padding: 0.25rem 1rem;
    position: relative;
    cursor: pointer;
    max-width: 160px;
    overflow: hidden;
    /* clip-path: inset(0px 0px 0px 0px round 20px); */
}
.hero .titleWrap .seasonChamp .seasonChampBg {
    display: inline-block;
    background-color: var(--yellow);
    height: 100%;
    position: absolute;
    border-radius: 20px;
    z-index: -1;
    transition: width 1s;
    border: 2px solid var(--yellow);
    width: 0;
    opacity: 1;
    left: -4px;
    top: 0;
}
.hero .titleWrap .seasonChamp:hover .seasonChampBg {
    width: 102%;
    opacity: 1;
    left: -2px;
    top: 0px;
}

.hero .heroCarousel {
    width: 100%;
    height: 100%;
    background-size: cover;
    background-position: center;
}

.session {
    padding: 7rem 0;
    max-width: 1060px;
    margin: 0 auto;
    text-align: center;
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
.champion .tabs {
    margin-bottom: 0;
}
.champion .tabContentWrap {
    padding: 0 2.5rem;
}
.champion .tabContentWrap h3.title {
    padding-left: 1rem;
}
.champion .tabContent {
    text-align: left;
}
.champion .podiumWrap {
    margin-bottom: 2rem;
}
.champion .podium {
    border-radius: 64px;
    height: 64px;
    position: relative;
    background-color: #ffffff7d;
    padding: 1rem 1rem 1rem 3rem;
    display: flex;
    justify-content: center;
}
.champion .podium.gold {
    background-color: #e8af057d;
}
.champion .profile {
    position: absolute;
    left: -1px;
    top: -1px;
    border-radius: 50%;
    width: 64px;
    height: 64px;
    background-color: var(--yellow);
    border: 4px solid #fff;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.champion .userName {
    font-weight: bold;
    align-self: center;
}
.champion .count {
    flex: 1 1 0;
    background-color: var(--yellow);
    font-weight: bold;
    padding: 1em;
    border-top: 1px solid #fff;
    border-bottom: 1px solid #fff;
    margin: 0 auto;
    font-weight: bold;
    font-size: 1.2em;
}

.gallery .tabs {
    display: flex;
    justify-content: space-between;
}
.gallery .tabs .inline {
    width: 620px;
}

.gallery .tabs .imgUp,
.gallery .tabs .imgDown {
    height: 384px;
    background-color: #ccc;
    margin-bottom: 40px;
    background-image: url("/vue_assets/img/main_hero/ps4yxbox_3109823b.jpeg");
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.gallery .tabs .imgDown {
    margin-bottom: 0;
    margin-top: 40px;
    display: flex;
    justify-content: center;
}
.gallery .tabs .imgDown > span {
    align-self: center;
    transition: all 0.5s;
}
.gallery .tabs .imgDown.scaleUp > span {
    transform: scale(120%);
}
.gallery .tabs .inline2 {
    width: 400px;
}
.gallery .tabs .squareWrap {
    display: flex;
    justify-content: space-between;
}
.gallery .tabs .square {
    width: 180px;
    height: 180px;
    background-color: #ccc;
    background-image: url("/vue_assets/img/main_hero/ps4yxbox_3109823b.jpeg");
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.gallery .tabs .imgBig,
.gallery .tabs .square {
    cursor: pointer;
}
.gallery .tabs .imgBig:hover,
.gallery .tabs .square:hover {
    filter: brightness(130%);
}

.contact .sns {
    min-height: 300px;
    margin: 0 20px;
    padding: 0;
    position: relative;
    background-color: #fff;
    cursor: pointer;
}
.contact .sns.twitch:hover {
    background-color: #6441a5;
}
.contact .sns.youtube:hover {
    background-color: #e52927;
}
.contact .sns.discord:hover {
    background-color: #7389dc;
}
.contact .sns.opentalk:hover {
    background-color: #ffe600;
}

.contact .sns .snsLogo {
    height: calc(100% - 47px);
    background-repeat: no-repeat;
    background-size: 50%;
    background-position: center;
}
.contact .sns.twitch .snsLogo {
    background-image: url("/vue_assets/img/TWITCH@2x.png");
}
.contact .sns.youtube .snsLogo {
    background-image: url("/vue_assets/img/YOUTUBE@2x.png");
}
.contact .sns.discord .snsLogo {
    background-image: url("/vue_assets/img/DISCORD@2x.png");
}
.contact .sns.opentalk .snsLogo {
    background-image: url("/vue_assets/img/KAKAO@2x.png");
}
.contact .sns.twitch:hover .snsLogo {
    background-image: url("/vue_assets/img/TWITCH_WHITE@2x.png");
}
.contact .sns.youtube:hover .snsLogo {
    background-image: url("/vue_assets/img/YOUTUBE_WHITE@2x.png");
}
.contact .sns.discord:hover .snsLogo {
    background-image: url("/vue_assets/img/DISCORD_WHITE@2x.png");
}
.contact .sns.opentalk:hover .snsLogo {
    background-image: url("/vue_assets/img/KAKAO_WHITE@2x.png");
}

.contact .sns .bottomTitle {
    background-color: #8a8a8a;
    min-height: 47px;
    font-weight: 800;
    font-size: 1.5rem;
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    line-height: 47px;
}
.contact .sns:hover .bottomTitle {
    background-color: rgba(255, 255, 255, 0.5);
    color: #fff;
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
