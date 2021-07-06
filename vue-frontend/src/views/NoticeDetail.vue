<template>
    <div class="home">
        <Header />

        <div class="session">
            <h1 class="title">
                {{ title }}
            </h1>
            <h3 class="subTitle">
                {{ writer }}
            </h3>
            <hr class="yellow" />
        </div>

        <div class="pageDivider">
            <span class="regDate">{{ data.regDt }}</span>
            <hr />
        </div>
        <div v-if="content" class="session notice content Staatliches mb-5">
            <p v-html="content"></p>
        </div>
        <div v-else class="session notice Staatliches text-center mb-5">
            <div class="row header">
                <div class="count">로딩중...</div>
            </div>
        </div>

        <div class="pageDivider">
            <hr class="last" />
        </div>

        <GoBack />
        <div class="text-center mb-5">
            <b-button variant="link" class="logo" to="/"></b-button>
        </div>
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header";
import GoBack from "@/components/GoBack";

export default {
    name: "Home",
    components: {
        Header,
        GoBack,
    },
    data() {
        return {
            title: "",
            writer: "",
            content: "",
            data: {},
            bbsSeq: null,
        };
    },
    created() {
        this.bbsSeq = this.$route.params.id;
        this._getContent();
    },
    mounted() {
        window.scrollTo(0, 0);
    },
    methods: {
        _getContent() {
            this.$axios
                .get(`/api/notice/detail/${this.bbsSeq}`, {
                    withCredentials: false,
                })
                .then((data) => {
                    this.data = data.data.data;
                    this.title = data.data.data.title;
                    this.writer = data.data.data.regId;
                    this.content = data.data.data.content;
                });
        },
        _styleRowCount(count) {
            return count < 10 ? `0${count}` : count;
        },
        onClickPage(page) {
            this.currentPage = page;
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
.session.notice.content {
    text-align: left;
}
.session:first-child {
    padding-top: 0;
}
.session:last-child {
    padding-bottom: 5rem;
}
.session h1.title {
    font-size: 2rem;
    font-family: Staatliches, Helvetica, Arial, sans-serif;
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

.session .row {
    display: flex;
    min-height: 46px;
    margin: 0.5rem 0;
    cursor: pointer;
}
.session .row.header {
    min-height: 50px;
    border-top: 2px solid #fff;
    border-bottom: 2px solid #fff;
    margin: 1rem 0;
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
.session .row .count {
    flex: 1 1 0;
    background-color: var(--yellow);
    font-weight: bold;
}
.session .row .title {
    flex: 10 1 0;
    color: #000;
    background-color: #fff;
}
.session .row.header .title {
    color: #8a8a8a;
}
.session .row .writer {
    flex: 1.5 1 0;
    background-color: #4d4d4d;
    text-transform: uppercase;
}
.pageDivider {
    max-width: 1140px;
    margin: 0 auto;
    text-align: right;
    font-size: 0.9rem;
    color: #8a8a8a;
}
.pageDivider > hr {
    height: 2px;
    margin-top: 0.5rem;
    background-color: #fff;
    opacity: 0.9;
    margin-bottom: 5rem;
}
.pageDivider > hr.last {
    margin: 5rem 0;
}

.paginationWrap {
    justify-content: center;
    display: flex;
    margin-bottom: 7rem;
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
