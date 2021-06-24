<template>
    <b-modal
        id="modal-scrollable"
        size="lg"
        scrollable
        v-model="showDetail"
        header-bg-variant="dark"
        body-bg-variant="dark"
        footer-bg-variant="dark"
        ok-only
        okTitle="Close"
        ok-variant="secondary"
        no-close-on-backdrop
    >
        <div class="subTitle">
            <h3>Kabkee</h3>
            <span class="ttscore yellow">TT SCORE: {{ 0 }}</span>
        </div>
        <div class="tabs list">
            <div class="session detail Staatliches text-center mb-5">
                <div class="row header">
                    <div class="track">TRACK</div>
                    <div class="carName">CAR</div>
                    <div class="bestlap">BEST LAP</div>
                    <div
                        class="rank"
                        @click="_setGapCriteria(results[0].bestLap)"
                    >
                        GAP
                    </div>
                    <div class="lap">LAP</div>
                </div>

                <div class="row Inter">
                    <div class="track">TRACK</div>
                    <div class="carName">CAR</div>
                    <div class="bestlap">BEST LAP</div>
                    <div
                        class="rank"
                        @click="_setGapCriteria(results[0].bestLap)"
                    >
                        GAP
                    </div>
                    <div class="lap">LAP</div>
                </div>
                <div class="row Inter">
                    <div class="track">TRACK</div>
                    <div class="carName">CAR</div>
                    <div class="bestlap">BEST LAP</div>
                    <div
                        class="rank"
                        @click="_setGapCriteria(results[0].bestLap)"
                    >
                        GAP
                    </div>
                    <div class="lap">LAP</div>
                </div>

                <!-- <div
                        class="row Inter"
                        v-for="(row, rowIdx) in filteredResults"
                        :key="rowIdx"
                        :data-seq="row.seq"
                    >
                        <div class="count" :class="{ top: row.rank <= 3 }">
                            <span>{{ row.rank }}</span>
                        </div>
                        <div class="nickname" @click="onClickPlayerDetail(row)">
                            {{ row.firstName }} {{ row.lastName }}
                        </div>
                        <div class="carName">{{ row.carName }}</div>
                        <div class="bestlap">{{ row.bestLap | secToMin }}</div>
                        <div class="gap" @click="_setGapCriteria(row.bestLap)">
                            {{ _getGap(row.bestLap) | secToMinForGap }}
                        </div>
                        <div class="bestSec">{{ row.sector1 | secToMin }}</div>
                        <div class="bestSec">{{ row.sector2 | secToMin }}</div>
                        <div class="bestSec">{{ row.sector3 | secToMin }}</div>
                        <div class="potential">
                            {{
                                (row.sector1 + row.sector2 + row.sector3)
                                    | secToMin
                            }}
                        </div>
                    </div>
                    <div class="row Inter" v-if="!filteredResults.length">
                        <div class="nickname">
                            결과 없습니다.
                        </div>
                    </div> -->
            </div>
        </div>
    </b-modal>
</template>

<script>
export default {
    props: ["showDetail", "playerDetail"],
    data() {},
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
* >>> .modal-content .modal-header {
    border: 0;
    padding: 1em 1em 0 1em;
}
* >>> .modal-content .subTitle > * {
    display: inline;
    vertical-align: middle;
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
* >>> .modal-content .row .track,
* >>> .modal-content .row .carName,
* >>> .modal-content .row .bestlap,
* >>> .modal-content .row .rank,
* >>> .modal-content .row .lap {
    flex: 1 1 0;
    background-color: #4d4d4d;
    text-transform: uppercase;
}
* >>> .modal-content .row .track {
    flex: 2 1 0;
}
* >>> .modal-content .row .carName {
    flex: 3 1 0;
}
* >>> .modal-content .row.Inter .bestlap {
    color: var(--yellow);
}
* >>> .modal-content .tabs {
    margin: 0;
    min-height: inherit;
}
</style>
