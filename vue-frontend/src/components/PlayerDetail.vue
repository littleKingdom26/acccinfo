<template>
    <b-modal
        id="modal-scrollable"
        size="lg"
        scrollable
        v-model="isShowDetail"
        header-bg-variant="dark"
        body-bg-variant="dark"
        footer-bg-variant="dark"
        ok-only
        okTitle="Close"
        ok-variant="secondary"
        no-close-on-backdrop
    >
        <div class="subTitle" ref='subTitle' v-if="lPlayerDetail && lPlayerDetail.length">
            <h3>
                {{ lPlayerDetail[0].firstsName }}
                {{ lPlayerDetail[0].lastName }}
            </h3>
            <img
                src="/vue_assets/img/steam ICON@3x.png"
                @click="onClickSteamLogo(filteredId(lPlayerDetail[0].playerId))"
            />
            <span class="ttscore yellow" v-if="carClass == 'GT3'"
                >TT SCORE: {{ playerTTscore }}</span
            >
            <div class='steamId' @click='onClickSteamId(filteredId(lPlayerDetail[0].playerId))'>{{filteredId(lPlayerDetail[0].playerId)}}</div>
        </div>
        <div class="tabs list">
            <img class='d-flex d-lg-none' :src='require(`@/assets/bg_hand.gif`)' />
            <div class="session detail Staatliches text-center mb-5">
                <div class="row header">
                    <div class="track">TRACK</div>
                    <div class="carName">CAR</div>
                    <div class="bestlap">BEST LAP</div>
                    <div class="rank">
                        RANK
                    </div>
                    <div class="lap">LAP</div>
                </div>

                <div
                    class="row Inter"
                    v-for="(lap, lapIdx) in lPlayerDetail"
                    :key="`${lap.playerId}_${lap.trackName}_${lapIdx}`"
                >
                    <div class="track">{{ lap.trackName }}</div>
                    <div class="carName">{{ lap.carName }}</div>
                    <div class="bestlap">{{ lap.bestLap | secToMin }}</div>
                    <div class="rank">{{ lap.rank }} / {{ lap.allPlayer }}</div>
                    <!-- 
                        v-b-tooltip.hover.top="
                            `${parseFloat(
                                (lap.rank / lap.allPlayer) * 100
                            ).toFixed(1)}%`
                        "
                     -->
                    <div class="lap">{{ lap.totalLap }}</div>
                </div>

                <div
                    class="row Inter"
                    v-if="!lPlayerDetail || !lPlayerDetail.length"
                >
                    <div class="track">
                        기록이 없습니다.
                    </div>
                </div>
            </div>
        </div>
    </b-modal>
</template>

<script>
import moment from "moment";

export default {
    props: {
        showDetail: {
            type: Boolean,
            required: true,
        },
        playerDetail: {
            type: Array,
            required: true,
        },
        playerTTscore: {
            type: Number,
            required: true,
        },
        carClass: {
            type: String,
            required: true,
        },
        _setShowDetail: {
            type: Function,
            required: true,
        },
    },
    data() {
        return {
            isShowDetail: false,
            lPlayerDetail: [],
        };
    },
    filters: {
        secToMin(sec) {
            let secString = String(sec);
            let secs = secString.slice(0, -3);
            let decimals = secString.slice(-3);
            let convertedSec = moment.utc(secs * 1000).format("mm:ss");
            return `${convertedSec}.${decimals}`;
        },
    },
    watch: {
        showDetail() {
            this.isShowDetail = this.$props.showDetail;
        },
        playerDetail() {
            this.lPlayerDetail = this.$props.playerDetail;
        },
        isShowDetail(val) {
            this.lPlayerDetail = [];
            this._setShowDetail(val);
        },
    },
    methods: {
        filteredId(playerId){
            if (playerId[0] == "S" || playerId[0] == "s") {
                playerId = playerId.slice(1, playerId.length);
            }
            return playerId;
        },
        onClickSteamLogo(playerId) {
            window.open(`https://steamcommunity.com/profiles/${playerId}`);
        },
        onClickSteamId(playerId){
            this.$copyText(playerId, this.$refs.subTitle).then(function (e) {
                alert('클립보드에 복사 되었습니다.')
                console.log(e)
            }, function (e) {
                alert('Can not copy')
                console.log(e)
            })
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
    min-height: 100vh;
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
.session .row {
    display: flex;
    min-height: 46px;
    margin: 0.5rem 0;
    font-weight: bold;
    font-size: 0.8em;
}
.session .row.header {
    min-height: 50px;
    border-top: 2px solid #fff;
    border-bottom: 2px solid #fff;
    margin: 1rem 0;
    font-size: 1em;
}
.session .row.header.empty {
    background-color: #171717;
    min-height: 400px;
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

* >>> .modal-content {
    background-color: #000;
    color: #fff;
}
* >>> .modal-content .bg-dark {
    background-color: #000 !important;
}
* >>> .modal-content .modal-header {
    border: 0;
    padding: 1em 1em 1em 1em;
}
* >>> .modal-content .modal-body {
    padding-top: 0;
}
* >>> .modal-content .subTitle > * {
    display: inline;
    vertical-align: middle;
}
.modal-content .subTitle img {
    max-width: 1.5em;
    margin-left: 0.5em;
    cursor: pointer;
}
.modal-content .subTitle > .ttscore {
    float: right;
    margin-top: 10px;
}
* >>> .modal-content .close {
    font-size: 1.2em;
    border: 1px solid #fff;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    color: #000;
    background-color: #fff;
    line-height: 0;
}
* >>> .modal-content .session {
    min-height: 0;
    padding: 0;
}
* >>> .modal-content .row {
    background-color: #4d4d4d;
}
* >>> .modal-content .row .track,
* >>> .modal-content .row .carName,
* >>> .modal-content .row .bestlap,
* >>> .modal-content .row .rank,
* >>> .modal-content .row .lap {
    flex: 1 1 0;
    text-transform: uppercase;
}
* >>> .modal-content .row .track {
    flex: 2 1 0;
}
* >>> .modal-content .row .carName {
    flex: 3 1 0;
}
* >>> .modal-content .row.Inter:nth-child(odd) {
    background-color: #262626;
}
* >>> .modal-content .row.Inter .bestlap {
    color: var(--yellow);
}
* >>> .modal-content .tabs {
    margin: 0;
    min-height: inherit;
}
* >>> .modal-content .tabs.list{
    padding-top: 1rem;
    position: relative;
}
* >>> .modal-content .tabs.list > img{
    position: absolute;
    right: 0;
    top: 4px;
}
* >>> .modal-content .tabs.list > div{
    min-width: 650px;
}

.steamId{
    font-size: 0.8rem;
    color: #666;
    display: block !important;
    cursor: pointer;
}
</style>
