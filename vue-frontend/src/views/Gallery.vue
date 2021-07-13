<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                GALLERY
            </h1>
            <h3 class="subTitle">
                Sim Racing User Gallery
            </h3>
            <hr class="yellow" />
        </div>

        <div class="session notice JeojuGthic mb-5">
            <div class="tabs">
                <b-button
                    class="roundedBtn"
                    :class="{ active: checkListType == 'gallery' }"
                    variant="outline-secondary"
                    @click="checkLeagueSchedule = 'wed'"
                    >Gallery</b-button
                >
                <b-button
                    class="roundedBtn"
                    :class="{ active: checkListType != 'gallery' }"
                    variant="outline-secondary"
                    @click="checkLeagueSchedule = 'sun'"
                    >Video</b-button
                >
            </div>
        </div>
        <div
            v-if="galleryContent.length"
            class="session notice JeojuGthic text-left mb-5"
        >
            <b-row>
                <b-col>
                    <b-input-group class="searchGroup">
                        <template #prepend>
                            <mdiMagnify />
                        </template>
                        <b-form-input
                            v-model="search"
                            placeholder="SEARCH"
                        ></b-form-input>
                    </b-input-group>
                </b-col>
                <b-col></b-col>
                <b-col style="text-align: right;"
                    ><b-button class="registerBtn">REGISTER</b-button></b-col
                >
            </b-row>
            <b-row>
                <b-col class="imgCardWrap"
                    ><b-card
                        class="imgCard"
                        v-for="(img, imgIdx) in filteredGalleryContent"
                        :key="`${currentPage}_${imgIdx}`"
                        @click="$router.push(`/gallery/${img.seq}`)"
                    >
                        <div class="imgWrap">
                            <div
                                class="img"
                                :style="
                                    `background-image: url(${img.mainFilePath})`
                                "
                            ></div>
                        </div>
                        <template #footer>
                            <b-card-text class="subject" :alt="img.title">{{
                                img.title
                            }}</b-card-text>
                            <b-card-text class="writer" :alt="img.regId">{{
                                img.regId
                            }}</b-card-text>
                        </template>
                    </b-card></b-col
                >
            </b-row>
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
import mdiChevronDownCircle from "vue-material-design-icons/ChevronDownCircle.vue";
import mdiMagnify from "vue-material-design-icons/Magnify.vue";

export default {
    name: "Home",
    components: {
        Header,
        Pagination,
        mdiChevronDownCircle,
        mdiMagnify,
    },
    data() {
        return {
            currentPage: 1,
            rows: 0,
            perPage: 15,
            galleryContent: [],
            checkListType: "gallery",
            search: "",
        };
    },
    created() {
        this._getContent();
    },
    computed: {
        filteredGalleryContent() {
            return this.galleryContent.filter((item) => {
                if (item.title.indexOf(this.search) != -1) {
                    return item;
                }
            });
        },
    },
    methods: {
        _getContent() {
            this.$axios
                .get(
                    "/api/gallery/list",
                    {
                        params: {
                            page: this.currentPage,
                            size: this.perPage,
                        },
                    },
                    { withCredentials: false }
                )
                .then((data) => {
                    console.info("data.data.data", data.data.data);
                    this.rows = data.data.data.totalElements;
                    this.galleryContent = data.data.data.content;
                });
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
.inline-flex {
    display: inline-flex;
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

.tabs {
    margin: 5rem 0;
}
.tabs .roundedBtn {
    border-radius: 20px;
    margin: 1rem;
    padding: 0.5rem 5rem;
    border-color: transparent;
    background-color: #fff;
}
.tabs .roundedBtn.active,
.tabs .roundedBtn:hover {
    background-color: var(--yellow);
}

hr.yellow {
    max-width: 50px;
    border-width: 3px;
    border-color: var(--yellow);
    margin: 1rem auto;
    border-style: solid;
    opacity: 1;
}

.searchGroup {
    border-radius: 25px;
    border: 1px solid #fff;
    max-width: 250px;
    background-color: #444444;
}
.searchGroup .input-group-prepend {
    padding: 6px;
    background-color: #fff;
    border-radius: 50% !important;
    transform: scale(0.6);
    color: #808080;
    position: absolute;
    left: 0;
}
.searchGroup input {
    background-color: transparent;
    box-shadow: none;
    border: 0;
    padding-left: 2em;
    text-align: center;
    color: #fff;
}
.registerBtn {
    border-radius: 25px;
    background-color: var(--yellow);
    padding: 0.5em 1.2em;
}
.imgCardWrap {
    padding: 0;
}
.imgCard {
    background-color: transparent;
    padding: 1em;
    display: inline-flex;
    width: calc(100% / 3 - 2em);
    height: 275px;
    margin: 1em;
    padding: 0;
    cursor: pointer;
}
.imgCard .card-body {
    padding: 0;
}
.imgCard .card-footer {
    padding: 0.5em 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.imgCard .card-footer .card-text {
    margin: 0;
    font-weight: bold;
    max-width: 50%;
    overflow: auto;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.imgCard .card-footer .card-text.writer {
    color: var(--yellow);
}
.imgCard .imgWrap {
    overflow: hidden;
    height: 225px;
}
.imgCard .imgWrap .img {
    height: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    border-radius: 20px;
    transition: all 1s;
}
.imgCard .imgWrap:hover .img {
    transform: scale(1.2);
    transition: all 1s;
}

.paginationWrap {
    justify-content: center;
    display: flex;
    margin-bottom: 7rem;
}

.lastBtn {
    border-radius: 25px;
    background-color: var(--yellow);
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
