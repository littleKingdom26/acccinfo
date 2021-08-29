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
                <label for="input_accident">항의 범주</label>
                <!-- <b-form-input
                    id="input_accident"
                    class="custom_input"
                    v-model="candidate"
                    placeholder="NICKNAME"
                    trim
                ></b-form-input> -->
                <b-dropdown
                    v-if="faults.length"
                    id="input_accident"
                    text=""
                    no-flip
                    no-caret
                    class="selectDropdown"
                >
                    <template #button-content>
                        <span>{{ fault_selection.value }}</span
                        ><mdiChevronDownCircle color="#8a8a8a" />
                    </template>
                    <b-dropdown-item
                        v-for="fault in faults"
                        :key="fault.key"
                        href="#"
                        @click.stop.prevent="onClickFaultDropdown(fault)"
                        >{{ fault.value }}</b-dropdown-item
                    >
                </b-dropdown>
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
                <b-button class="lastBtn" @click="_postContent()"
                    ><span>REGISTER</span></b-button
                >
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
import mdiChevronDownCircle from "vue-material-design-icons/ChevronDownCircle.vue";

export default {
    name: "Home",
    components: {
        Header,
        mdiChevronDownCircle,
    },
    data() {
        return {
            applicant: "",
            candidate: "",
            race_event: "",
            session: "",
            replay_time: "",
            description: "",
            fault_selection: {
                value: "01. 비매너 주행",
                key: "unManner",
            },
            faults: [],
        };
    },
    created() {},
    mounted() {
        this._getComplaintsCode();
    },
    methods: {
        _getComplaintsCode() {
            let postData = {};
            this.$axios
                .get(`/api/common/complaintsCode`, {
                    withCredentials: false,
                })
                .then((data) => {
                    this.faults = data.data.data;
                    // console.info("this.faults", this.faults);
                });
        },
        _postContent() {
            if (
                !this.fault_selection.key |
                !this.description |
                !this.race_event |
                !this.applicant |
                !this.candidate |
                !this.replay_time |
                !this.session
            ) {
                this.$bvModal.msgBoxOk("입력란이 비어있습니다. 확인해주세요.", {
                    title: "확인",
                    size: "sm",
                    buttonSize: "sm",
                    okVariant: "danger",
                    headerClass: "p-2 border-bottom-0",
                    footerClass: "p-2 border-top-0",
                    centered: true,
                });
                return;
            }
            let postData = {
                complaints: this.fault_selection.key,
                description: this.description,
                event: this.race_event,
                regId: this.applicant,
                reviewTarget: this.candidate,
                replayTime: this.replay_time,
                sessionDivision: this.session,
            };
            this.$axios
                .post(`/api/reviewRequest`, postData, {
                    withCredentials: false,
                })
                .then((data) => {
                    if (data.data.success) {
                        this.fault_selection = {
                            value: "01. 비매너 주행",
                            key: "unManner",
                        };
                        this.description = "";
                        this.race_event = "";
                        this.applicant = "";
                        this.candidate = "";
                        this.replay_time = "";
                        this.session = "";

                        this.$bvModal.msgBoxOk("정상 등록 되었습니다.", {
                            title: "확인",
                            size: "sm",
                            buttonSize: "sm",
                            okVariant: "success",
                            headerClass: "p-2 border-bottom-0",
                            footerClass: "p-2 border-top-0",
                            centered: true,
                        });
                    } else {
                        this.$bvModal.msgBoxOk(
                            "시스템 오류입니다. 다시 시도해 주세요.",
                            {
                                title: "확인",
                                size: "sm",
                                buttonSize: "sm",
                                okVariant: "danger",
                                headerClass: "p-2 border-bottom-0",
                                footerClass: "p-2 border-top-0",
                                centered: true,
                            }
                        );
                    }
                });
        },
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
