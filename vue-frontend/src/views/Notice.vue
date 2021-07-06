<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                FINAL LAP NOTICE
            </h1>
            <h3 class="subTitle">
                FINAL LAP Official Notice
            </h3>
            <hr class="yellow" />
        </div>

        <div
            v-if="noticeContent.length"
            class="session notice Staatliches text-center mb-5"
        >
            <div class="row header">
                <div class="count">NO</div>
                <div class="title">TITLE</div>
                <div class="writer">WRITER</div>
            </div>

            <div
                class="row Inter"
                v-for="(row, rowIdx) in noticeContent"
                :key="rowIdx"
                :data-seq="row.seq"
                @click="$router.push(`/notice/${row.seq}`)"
            >
                <div class="count">{{ _getRowNumber(rowIdx) }}</div>
                <div class="title JeojuGthic">{{ row.title }}</div>
                <div class="writer JeojuGthic">{{ row.regId }}</div>
            </div>
        </div>
        <div v-else class="session notice Staatliches text-center mb-5">
            <div class="row header">
                <div class="count">로딩중...</div>
            </div>
        </div>
        <div v-if="rows / perPage > 1" class="paginationWrap Inter">
            <Pagination
                :currentPage="currentPage"
                :rows="rows"
                :perPage="perPage"
                :onClickPage="onClickPage"
            />
        </div>

        <div class="text-center mb-5">
            <b-button variant="link" class="logo" to="/"></b-button>
        </div>
    </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header";
import Pagination from "@/components/Pagination";

export default {
    name: "Home",
    components: {
        Header,
        Pagination,
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
    font-family: Staatliches, cursive, Helvetica, Arial, sans-serif;
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
