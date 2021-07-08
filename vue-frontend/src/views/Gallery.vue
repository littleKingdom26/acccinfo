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
        <div class="session notice JeojuGthic text-left mb-5">
            <b-row>
                <b-col>Search</b-col>
                <b-col></b-col>
                <b-col><b-button>REGISTER</b-button></b-col>
            </b-row>
            <b-row>
                <b-col><b-card></b-card></b-col>
                <b-col><b-card></b-card></b-col>
                <b-col><b-card></b-card></b-col>
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
import mdiChevronDownCircle from "vue-material-design-icons/ChevronDownCircle.vue";

export default {
    name: "Home",
    components: {
        Header,
        mdiChevronDownCircle,
    },
    data() {
        return {
            checkListType: 'gallery',
        };
    },
    created() {},
    methods: {
        onClickFaultDropdown(fault) {
            this.fault_selection = fault;
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
.input_group {
    margin: 1em 0;
}
.input_group label {
    color: var(--yellow);
    font-weight: bold;
    margin-bottom: 0.5em;
}
.input_group .selectDropdown {
    display: block;
    width: 100%;
}
.input_group .selectDropdown >>> button {
    width: 100%;
    background-color: #262626;
    color: #8a8a8a;
    text-align: left;
}
.input_group .selectDropdown >>> button .chevron-down-circle-icon {
    float: right;
}
.input_group .selectDropdown >>> .dropdown-menu {
    margin-top: 0.5em;
    background-color: #262626;
    text-align: center;
    width: 100%;
}
.input_group .selectDropdown >>> .dropdown-menu .dropdown-item {
    color: #fff;
}
.input_group .selectDropdown >>> .dropdown-menu .dropdown-item:hover,
.input_group .selectDropdown >>> .dropdown-menu .dropdown-item:focus {
    background-color: #555;
}
.custom_input {
    background-color: #262626;
    color: #fff;
    border: 0;
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
