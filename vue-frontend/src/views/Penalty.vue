<template>
    <div class="home">
        <Header />

        <div class="session mb-5">
            <h1 class="title">
                PENALTY
            </h1>
            <h3 class="subTitle">
                Review Request Board
            </h3>
            <hr class="yellow" />
        </div>

        <div class="session notice JeojuGthic mb-5">
            <h2>심의 신청</h2>
            <p>
                경기 내 부여된 페널티 이외의 상황에 대해 심의 신청을 할 수
                있습니다.<br />심의 신청기간은 레이스 종료 후 24시간 이내까지
                신청해주시기 바랍니다.
            </p>
        </div>
        <div class="session notice JeojuGthic text-left mb-5">
            <div role="group" class="input_group">
                <label for="input_applicant">심의 신청자</label>
                <b-form-input
                    id="input_applicant"
                    class="custom_input"
                    v-model="applicant"
                    placeholder="NICKNAME"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_candidate">심의 대상자</label>
                <b-form-input
                    id="input_candidate"
                    class="custom_input"
                    v-model="candidate"
                    placeholder="NICKNAME"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_candidate">항의 범주</label>
                <b-form-input
                    id="input_candidate"
                    class="custom_input"
                    v-model="candidate"
                    placeholder="NICKNAME"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_race_event">레이스 이벤트</label>
                <b-form-input
                    id="input_race_event"
                    class="custom_input"
                    v-model="race_event"
                    placeholder="EX: 21년 6월 27일 일요리그"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_session">세션 단계</label>
                <b-form-input
                    id="input_session"
                    class="custom_input"
                    v-model="session"
                    placeholder="EX: 프로리그 / 레이스"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_replay_time">리플레이 시간</label>
                <b-form-input
                    id="input_replay_time"
                    class="custom_input"
                    v-model="replay_time"
                    placeholder="EX: 0랩 00분 00초"
                    trim
                ></b-form-input>
            </div>
            <div role="group" class="input_group">
                <label for="input_description">사건 설명</label>
                <b-form-textarea
                    id="input_description"
                    class="custom_input"
                    v-model="description"
                    placeholder="사건 설명"
                    rows="6"
                    max-rows="6"
                    trim
                ></b-form-textarea>
            </div>
            <div class="text-right">
                <b-button class="lastBtn"><span>REGISTER</span></b-button>
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

export default {
    name: "Home",
    components: {
        Header,
        mdiChevronRightCircle,
    },
    data() {
        return {};
    },
    created() {},
    methods: {},
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
.input_group {
    margin: 1em 0;
}
.input_group label {
    color: var(--yellow);
    font-weight: bold;
    margin-bottom: 0.5em;
}
.custom_input {
    background-color: #262626;
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
