<template>
    <div id="app" class="wrap">
        <router-view />
    </div>
</template>

<script>
export default {
    name: "App",
};
</script>

<style scoped>
.wrap {
    overflow: scroll;
}
.wrap::-webkit-scrollbar {
    display: none;
}
</style>