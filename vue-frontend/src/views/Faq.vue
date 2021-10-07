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

        <div class="session notice JeojuGthic text-left mb-5">
            <div class="accordion" role="tablist">
                <b-card no-body v-for="(faq, faqIdx) in faqs" :key="faqIdx">
                    <b-card-header
                        v-b-toggle="`accordion_${faqIdx}`"
                        header-tag="header"
                        role="tab"
                        :class="{ active: faq.open }"
                    >
                        <div class="inline-flex">
                            <span class="firstSector">Q:</span
                            ><span class="secondSector">
                                <span>{{ faq.question }}</span
                                ><mdiChevronRightCircle
                                    :color="faq.open ? '#ffffff' : '#8a8a8a'"
                            /></span>
                        </div>
                    </b-card-header>
                    <b-collapse
                        :id="`accordion_${faqIdx}`"
                        role="tabpanel"
                        v-model="faq.open"
                    >
                        <b-card-body>
                            <div class="inline-flex">
                                <span class="firstSector">A:</span
                                ><span
                                    class="secondSector"
                                    v-html="faq.answer"
                                ></span>
                            </div>
                        </b-card-body>
                    </b-collapse>
                </b-card>
            </div>
        </div>
        <div class="tabs lastBtnWrap Staatliches mb-5">
            <b-row>
                <b-col cols='12' md='4'>
                    <b-button class="lastBtn" to="/notice/131"
                        ><span>문의하기</span>
                        <mdiChevronRightCircle color="#ffffff"
                    /></b-button>
                </b-col>
                <b-col cols='12' md='4'>
                    <b-button class="lastBtn" to="/notice/93"
                        ><span>마음의 소리</span>
                        <mdiChevronRightCircle color="#ffffff"
                    /></b-button>
                </b-col>
                <b-col cols='12' md='4'>
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
            faqs: [
                {
                    open: false,
                    question: "타임트라이얼은 어떻게 참가하나요?",
                    answer: `[서버 접속 방법]<br />
<br />
서버목록 검색창에 'JAJUKU' 입력 시 Time Trial 공식 서버가 검색이 됩니다.<br />
<br />
Server : [KOREA] JAJUKU Time Trial (track) / www.FinalLap.online<br />
<br />
Password : accc<br />
<br />
 [주요 내용] <br />
<br />
1. 기간조회와 서킷 조회가 가능합니다. 해당 서킷 선택 시 지금까지 기록된 누적기록입니다.<br />
2. 드라이버 클릭 시 해당 사용자의 트랙별 기록 확인 가능합니다. <br />
3. 포텐셜 기록은 베스트 섹션 1~3을 더한 기록으로 본인의 잠재된 베스트를 의미합니다.<br />
4. 기록은 서버에 모든 유저가 퇴장했을 때, 결과 값이 작성되며 해당 페이지로 등록됩니다.<br />
5. 매주 월요일 오전 서킷이 변경됩니다.`,
                },
                {
                    open: false,
                    question:
                        "프로리그 / 마스터리그 / 원메이크 리그의 차이점은 무엇인가요?",
                    answer: `[원메이크 리그]<br/>
: 매주 수요일 진행되며 지정된 차량으로 달리는 레이스<br/>
<br/>
[마스터 리그] <br/>
: 첫 일요 리그 참가 시 기본적으로 마스터리그 부터 진행<br/>
<br/>
[프로 리그] <br/>
: 마스터 리그에서 시즌종료 결과, 상위권을 기록할 경우 프로 리그로 승격`,
                },
                {
                    open: false,
                    question: "리그참여는 어떻게 하나요?",
                    answer: `기본적으로 SA(안전점수)65점 이상, 트랙메달 2개 조건이 달성되면 참여 가능합니다.`,
                },
                {
                    open: false,
                    question: "엔트리 등록은 어떻게 하나요?",
                    answer: `[엔트리 등록 방법]<br/>
: 레지스터 탭에 접속 후 본인의 ' 닉네임 / 스팀 프로필 / 해당시즌 달릴 차량 ' 선택 후 제출하시면 됩니다.<br/>
<br/>
[스팀 프로필 보는 방법]<br/>
: 스팀 프로필 수정에 들어가셔서 일반 ▶ 내 프로필 URL ▶ URL 뒷 숫자 17자리 복사 하여 제출하시면 됩니다.`,
                },
                {
                    open: false,
                    question: "페널티 규정과 심의는 어떻게 진행되나요?",
                    answer: `페널티는 레이스 중 일어나는 전 사고에 대해 심의자가 레이스 종료 후 페널티를 부여하게 됩니다.<br/>
* 자세한 규정은 해당 공지글 참조`,
                },
                {
                    open: false,
                    question: "정규 리그 이외에 진행되는 레이스가 있나요?",
                    answer: `정규리그 외 진행되는 레이스는 매일 밤 10시 12시에 연습레이스가 진행됩니다.<br/>
내구 레이스 및 기타 이벤트레이스는 이벤트게시판에 공지하니 참고해 주시기 바랍니다.`,
                },
                {
                    open: false,
                    question: "초보인데 리그 참여 가능한가요?",
                    answer: `초보분들도 SA(안전점수) 65점 이상, 트랙메달 2개 조건에 달성하실 경우 리그에 참여하실 수 있습니다.<br/>
단, 파이널랩 공지에 등록되 있는 초보자 레이스 가이드를 숙지하시기 바랍니다.`,
                },
                {
                    open: false,
                    question: "SA점수는 어떻게 올리나요?",
                    answer: `SA점수는 사고가 안날 시 점수가 자동으로 올라갑니다.<br/>
주변 차량과 근접한 위치에서 지속적으로 사고없이 달릴 경우 SA점수는 보다 빠르게 올라갑니다.<br/>
싱글 또한 AI와 같이 달릴 경우 멀티플레이와 같이 SA점수가 올라갑니다.`,
                },
                {
                    open: false,
                    question: "마음의 소리는 무엇인가요?",
                    answer: `현재 운영에 대한 불만 또는 제안사항 등을 익명으로 운영진에게 건의하는 기능입니다.<br/>
마음껏 보내주세요~`,
                },
                {
                    open: false,
                    question: "심의 신청은 어떻게 하나요?",
                    answer: `PENALTY 카테고리에서 심의 신청을 하시면 됩니다.<br/>
단, 경기내의 부과된 페널티(푸싱으로 인한 초 페널티 등) 이외의 경우에만 신청 가능합니다.`,
                },
            ],
        };
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
    width: 100%;
}
.session {
    padding: 2rem 0;
    padding-top: 7rem;
    /* max-width: 1060px; */
    max-width: 1140px;
    margin: 0 auto;
    text-align: center;
}
@media (max-width: 980px) {
    .session {
        padding-top: 9rem;
    }
}
@media (max-width: 768px) {
}
@media (max-width: 536px) {
    .session {
        padding-top: 12rem;
    }
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

.row{
    margin: 0;
}

.accordion {
    text-align: left;
    padding: 0.5rem;
}
.accordion .card {
    background-color: transparent;
    margin: 0;
    margin-top: 0.5em;
    margin-bottom: 0.5em;
}
.accordion .card-header {
    min-height: 50px;
    display: flex;
    align-items: center;
    border-radius: 25px;
    padding: 1em;
    font-weight: bold;
    border: 1px solid #fff;
}
.accordion .card-header.active {
    background-color: var(--yellow);
    border: 0;
}
.accordion .card-body {
    background-color: #fff;
    border-radius: 25px;
    margin-top: 0.5em;
    color: #000;
}
.accordion .firstSector {
    flex: 1 1 0;
    margin-right: 0.5em;
}
.accordion .secondSector {
    flex: 99 1 0;
}
.accordion .chevron-right-circle-icon {
    float: right;
    transform: rotate(90deg);
}

.lastBtnWrap {
    max-width: 1140px;
    margin: 0 auto;
    margin-top: 3em;
}
.lastBtnWrap .lastBtn {
    width: 100%;
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
