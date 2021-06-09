<template>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls=""
      first-text="<<"
      prev-text="<"
      next-text=">"
      last-text=">>"
    ></b-pagination>
</template>

<script>
export default {
    props:{
        currentPage:{
            default: 1,
        },
        rows:{
            default: 0,
        },
        perPage:{
            default: 20,
        },
    },
    data(){
        return{
        }
    }
}
</script>

<style scoped>
* >>> .page-item > .page-link{
    background-color: transparent;
    color: #fff;
    border: 0;
}
* >>> .page-item.disabled > .page-link{
    color: #666;
}
* >>> .page-item.active .page-link{
    background-color: transparent;
    color: var(--yellow);
    border: 0;
}
</style>