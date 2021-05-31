<template>
    <div class="home">
        <div class="header">
            <b-button variant="link" class="logo" to="/"></b-button>

            <div class="menus Staatliches">
                <b-button
                    variant="link"
                    v-for="(menu, menuIdx) in menus"
                    :key="menuIdx"
                    :to="menu.to"
                    :class="{
                        active: menu.to == $router.currentRoute.path,
                    }"
                >
                    {{ menu.title }}
                </b-button>
            </div>
        </div>
        <div class="hero">
            <div class="titleWrap Staatliches">
                <h1 class="title">
                    <span><span class="yellow">24 HOURS</span> OPEN</span><br />
                    <span>TIMETRIAL SERVER</span>
                </h1>
                <div class="trophy"></div>
                <div class="seasonChamp">
                    <div class="seasonChampBg" />
                    SEASON CHAMPION
                </div>
            </div>
            <div class="heroCarousel"></div>
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

            <div class="tabs">
                <b-row>
                    <b-col class="tabContentWrap">
                        <h2 class="title">PRO LEAGUE</h2>
                        <div class="tabContent Inter">
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                        </div>
                    </b-col>
                    <b-col class="tabContentWrap">
                        <h2 class="title">MASTER LEAGUE</h2>
                        <div class="tabContent Inter">
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                        </div>
                    </b-col>
                    <b-col class="tabContentWrap">
                        <h2 class="title">ONE MAKE LEAGUE</h2>
                        <div class="tabContent Inter">
                            <div class="podiumWrap">
                                <h3 class="title">1ST</h3>
                                <div class="podium gold text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">2ND</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                            <div class="podiumWrap">
                                <h3 class="title">3RD</h3>
                                <div class="podium text-center">
                                    <div class="profile"></div>
                                    <span class="userName">SWEET SRD</span>
                                </div>
                            </div>
                        </div>
                    </b-col>
                </b-row>
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
            <div class="tabs"></div>
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

export default {
    name: "Home",
    components: {},
    data() {
        return {
            checkLeagueSchedule: "wed",
            menus: [
                { title: "HOME", to: "/" },
                { disabled: true, title: "NOTICE", to: "/notice" },
                { disabled: true, title: "LEAGUE", to: "/league" },
                { disabled: true, title: "TIME TRIAL", to: "/timetrial" },
                { disabled: true, title: "RESULT", to: "/result" },
                { disabled: true, title: "EVENT", to: "/event" },
                { disabled: true, title: "GALLERY", to: "/gallery" },
                { disabled: true, title: "REGISTER", to: "/register" },
                { disabled: true, title: "FAQ", to: "/faq" },
            ],
            snsTypes: [
                { type: "twitch", title: "TWITCH", url: "https://twitch.com" },
                {
                    type: "youtube",
                    title: "YOUTUBE",
                    url: "https://youtube.com",
                },
                {
                    type: "discord",
                    title: "DISCORD",
                    url: "https://discord.com",
                },
                {
                    type: "opentalk",
                    title: "OPEN TALK",
                    url: "https://opentalk.com",
                },
            ],
        };
    },
    mounted() {
        console.info(this.$router.currentRoute);
    },
    methods: {
        onClickOpenUrl(link) {
            window.open(link);
        },
    },
};
</script>

<style scoped>
.yellow {
    color: var(--yellow);
}
.header {
    max-width: 1140px;
    margin: 0 auto;
    margin-top: 0.5rem;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 100;
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
.menus {
    max-width: 50%;
    min-width: 670px;
    width: 100%;
    float: right;
    margin-top: 10px;
    text-align: right;
}
.menus ul {
    list-style-type: none;
    margin: 0;
}
.menus > a {
    text-decoration: none;
    color: #fff;
    padding: 0.25rem 1rem;
}
.menus > a:hover,
.menus > a.active {
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
.hero .titleWrap .seasonChamp {
    margin: 0 auto;
    border-radius: 20px;
    font-size: 1rem;
    border: 2px solid var(--yellow);
    padding: 0.25rem 1rem;
    display: initial;
    position: relative;
    cursor: pointer;
    clip-path: inset(0 0 0 0 round 100px);
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
}

.hero .heroCarousel {
    width: 100%;
    height: 100%;
    background-image: url("/vue_assets/img/ps4yxbox_3109823b.jpeg");
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
}
.champion .userName {
    font-weight: bold;
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
</style>
