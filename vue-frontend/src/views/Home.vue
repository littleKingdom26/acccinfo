<template>
    <div class="home">
        <div class="header">
            <div class="logo"></div>
            <div class="menus Staatliches">
                <ul>
                    <li v-for="(menu, menuIdx) in menus" :key="menuIdx">
                        {{ menu.title }}
                    </li>
                </ul>
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
            <div class="carousel"></div>
        </div>
        <div class="session schedule">
            <h1 class="title">
                LEAGUE SCHEDULE
            </h1>
            <h3 class="subTitle">
                Here Is Our Schedule
            </h3>
            <hr class="yellow" />
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
            menus: [
                { title: "HOME" },
                { title: "NOTICE" },
                { title: "LEAGUE" },
                { title: "TIME TRIAL" },
                { title: "RESULT" },
                { title: "EVENT" },
                { title: "GALLERY" },
                { title: "REGISTER" },
                { title: "FAQ" },
            ],
        };
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
    margin-top: 27px;
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
    min-width: 460px;
    width: 100%;
    float: right;
}
.menus ul {
    list-style-type: none;
    display: flex;
    margin: 0;
    justify-content: space-between;
}

.hero {
    height: 100vh;
    width: 100%;
    max-width: 1320px;
    max-height: 723px;
    margin: 0 auto;
    position: relative;
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
.hero .titleWrap .seasonChampBg {
    display: inline-block;
    background-color: var(--yellow);
    height: 100%;
    width: 100%;
    position: absolute;
    top: -2px;
    left: -2px;
    border-radius: 20px;
    z-index: -1;
    transition: width 1s;
    border: 2px solid var(--yellow);
    opacity: 0;
    animation-duration: 1s;
    animation-name: slideout;
}
.hero .titleWrap .seasonChamp:hover .seasonChampBg {
    animation-duration: 1s;
    animation-name: slidein;
    opacity: 1;
}
@keyframes slidein {
    from {
        width: 0%;
        opacity: 1;
    }
    to {
        width: 100%;
        opacity: 1;
    }
}
@keyframes slideout {
    to {
        width: 0%;
        opacity: 1;
    }
    from {
        width: 100%;
        opacity: 1;
    }
}

.hero .carousel {
    width: 100%;
    height: 100%;
    background-image: url("/vue_assets/img/ps4yxbox_3109823b.jpeg");
    background-size: cover;
    background-position: center;
}

.session {
    padding-top: 10rem;
    max-width: 1060px;
    margin: 0 auto;
    text-align: center;
}
.subTitle {
    margin-top: 2rem;
    color: #8a8a8a;
    font-weight: 100;
}
hr.yellow {
    max-width: 50px;
    border-width: 3px;
    border-color: var(--yellow);
    margin-top: 2rem;
    border-style: solid;
}
</style>
