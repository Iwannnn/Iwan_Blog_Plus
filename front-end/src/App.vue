<template>
    <div id="app">
        <router-view />
    </div>
</template>

<script>
export default {
    name: "App",
    metaInfo() {
        return {
            title:
                this.$store.state.settings.dynamicTitle &&
                this.$store.state.settings.title,
            titleTemplate: (title) => {
                return title
                    ? `${title} - ${process.env.VUE_APP_TITLE}`
                    : process.env.VUE_APP_TITLE;
            },
        };
    },
    created() {
        setTimeout(() => {
            window.L2Dwidget.init({
                pluginRootPath: "live2dw/",
                pluginJsPath: "lib/",
                pluginModelPath: "live2d-widget-model-miku/assets/",
                tagMode: false,
                debug: false,
                model: {
                    jsonPath:
                        "../live2dw/live2d-widget-model-miku/assets/miku.model.json",
                },
                display: { position: "left", width: 200, height: 450 },
                mobile: { show: true },
                log: false,
            });
        });
    },
};
</script>