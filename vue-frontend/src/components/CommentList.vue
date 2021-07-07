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
                <b-col cols="3" class="column">
                    <b-form-input
                        v-model="writer"
                        placeholder="WRITER"
                        trim
                    ></b-form-input>
                </b-col>
                <b-col cols="7" class="column">
                    <b-form-input
                        v-model="commnet"
                        placeholder="COMMENT"
                        trim
                    ></b-form-input>
                </b-col>
                <b-col cols="2">
                    <b-button>REGISTER</b-button>
                </b-col>
            </b-row>
        </li>
    </ul>
</template>

<script>
export default {
    props: {
        comments: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            writer: "",
            commnet: "",
        };
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

        .column {
            background: transparent;
            border: 1px solid var(--yellow);
        }
        .column:nth-child(even) {
            width: calc(58.3333333333% - 0.5em - 0.5em);
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
</style>
