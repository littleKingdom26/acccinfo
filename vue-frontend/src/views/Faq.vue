<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                FAQ
            </h1>
            <h3 class="subTitle">
                Frequently Asked Question
            </h3>
            <hr class="yellow" />
        </div>

        <div class="session notice Staatliches text-center mb-5"></div>
        <div class="tabs lastBtnWrap Staatliches mb-5">
            <b-row>
                <b-col>
                    <b-button class="lastBtn" to="/notice/131"
                        ><span>문의하기</span>
                        <mdiChevronRightCircle color="#ffffff"
                    /></b-button>
                </b-col>
                <b-col>
                    <b-button class="lastBtn" to="/notice/93"
                        ><span>마음의 소리</span>
                        <mdiChevronRightCircle color="#ffffff"
                    /></b-button>
                </b-col>
                <b-col>
                    <b-button class="lastBtn" to="/notice/64"
                        ><span>후원하기</span>
                        <mdiChevronRightCircle color="#ffffff"
                    /></b-button>
                </b-col>
            </b-row>
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

export default {
    name: "Home",
    components: {
        Header,
        mdiChevronRightCircle,
    },
    data() {
        return {
            currentPage: 1,
            rows: 0,
            perPage: 15,
            noticeContent: [],
        };
    },
    created() {
        this._getContent();
    },
    methods: {
        _getRowNumber(idx) {
            let lineNumber =
                this.rows - (this.currentPage - 1) * this.perPage - idx;
            if (lineNumber > 0) {
                lineNumber = lineNumber < 10 ? `0${lineNumber}` : lineNumber;
            } else {
                lineNumber = "";
            }
            return lineNumber;
        },
        _getContent() {
            this.$axios
                .get(
                    "/api/notice/list",
                    {
                        params: {
                            page: this.currentPage,
                            size: this.perPage,
                        },
                    },
                    { withCredentials: false }
                )
                .then((data) => {
                    this.rows = data.data.data.totalElements;
                    this.noticeContent = data.data.data.content;
                });
        },
        _styleRowCount(count) {
            return count < 10 ? `0${count}` : count;
        },
        onClickPage(page) {
            this.currentPage = page;
            this._getContent();
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

.lastBtnWrap {
    max-width: 1060px;
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
