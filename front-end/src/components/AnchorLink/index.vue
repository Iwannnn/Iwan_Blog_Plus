<template>
    <div class="toc">
        <a-anchor-link
            v-for="item in list"
            :key="item.anchor"
            :href="`#${item.anchor}`"
            :title="item.text"
        >
            <AnchorLink v-if="item.children" :list="item.children" />
        </a-anchor-link>
    </div>
</template>
<script>
import AnchorLink from "@/components/AnchorLink";
export default {
    name: "AnchorLink",
    components: {
        AnchorLink,
    },
    props: {
        list: {
            type: Array,
            default: () => [],
        },
    },
};
</script>
<style scoped>
.toc {
    text-align: left;
}
</style>
