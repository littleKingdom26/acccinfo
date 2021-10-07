<template>
    <ul class="list" v-if="comments">
        <li class="item list" v-for="(cmt, cmtIdx) in comments" :key="cmtIdx">
            <b-row>
                <b-col cols="3" class="column writer"
                    ><span>{{ cmt.regId }}</span></b-col
                >
                <b-col cols="9" class="column comment"
                    ><span>{{ cmt.comment }}</span></b-col
                >
            </b-row>
        </li>
        <li class="item input">
            <b-row>
                <b-col cols='4' md="3" class="column">
                    <b-form-input
                        v-model="writer"
                        placeholder="WRITER"
                        trim
                    ></b-form-input>
                </b-col>
                <b-col cols='8' md="7" class="column">
                    <b-form-input
                        v-model="commnet"
                        placeholder="COMMENT"
                        trim
                    ></b-form-input>
                </b-col>
                <b-col cols='12' md="2">
                    <b-button @click="onClickRegister()">REGISTER</b-button>
                </b-col>
            </b-row>
        </li>
    </ul>
</template>

<script>
export default {
    props: {
        comments: {
            type: Array,
            required: true,
        },
        bbsSeq: {
            required: true,
        },
        getRefreshData: {
            type: Function,
            required: true,
        },
    },
    data() {
        return {
            writer: "",
            commnet: "",
        };
    },
    methods: {
        onClickRegister() {
            if (!this.bbsSeq) {
                this.$bvModal.msgBoxOk(
                    "게시물이 올바르지 않습니다. 확인해주세요.",
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
                return;
            }
            if (!this.writer | !this.commnet) {
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
                bbsSeq: this.bbsSeq,
                comment: this.commnet,
                regId: this.writer,
            };
            this.$axios
                .post(`/api/common/bbs/comment`, postData, {
                    withCredentials: false,
                })
                .then((data) => {
                    if (data.data.success) {
                        this.$bvModal.msgBoxOk("정상 등록 되었습니다.", {
                            title: "확인",
                            size: "sm",
                            buttonSize: "sm",
                            okVariant: "success",
                            headerClass: "p-2 border-bottom-0",
                            footerClass: "p-2 border-top-0",
                            centered: true,
                        });
                        this.comment = "";
                        this.writer = "";
                        this.getRefreshData();
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
    },
};
</script>

<style scoped lang="scss">
.list {
    list-style-type: none;
    padding: 0;
    text-align: left;

    .item {
        margin: 0.5em 0;

        .column {
            background-color: #262626;
            padding: 0.5em 1em;
            border-radius: 25px;
            color: var(--yellow);
            font-weight: bold;
            justify-content: center;
            display: flex;

            &.comment {
                justify-content: start;
            }
            &.writer span {
                align-self: center;
            }
        }
        .column:nth-child(even) {
            margin-left: 0.5em;
            text-align: left;
            color: #fff;
            width: calc(75% - 0.5em);
        }
    }
    .item:nth-child(even) {
        .column {
            background-color: #171717;
        }
    }

    .item.input {
        margin-top: 2em;
        padding: 0.5rem;

        .column {
            background: transparent;
            border: 1px solid var(--yellow);
            margin-bottom: 0.5rem;
        }
        .column:nth-child(even) {
            width: calc(58.3333333333% - 0.5em - 0.5em);
        }
        @media (max-width: 767px) {
            .column:nth-child(even) {
                width: calc(64% - 0.5em);
            }
        }

        ::v-deep input,
        ::v-deep input:focus {
            background-color: transparent;
            border: 0;
            box-shadow: none;
            text-align: center;
            color: #fff;
        }

        button {
            background-color: var(--yellow);
            height: 100%;
            width: 100%;
            border-radius: 25px;
        }
    }
    ::v-deep .col-2 {
        padding: 0;
        margin-left: 0.5em;
    }
}
.row{
    margin: 0;
}
</style>
