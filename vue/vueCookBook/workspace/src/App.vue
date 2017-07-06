<template>
    <div id="app">
        <article>
            fruit.<br>
            They call me
            They call me	fish.<br>
            They call me	insect.<br>
            But actually	I'm not one of those.

            <div id="solution" @click="showSolution = true">
                I	am a <transition name="fade"> <span id="dragon" v-show="showSolution">Dragon</span></transition>
            </div>
        </article>
    </div>
</template>

<script>
    export default {
        name: 'app',
        data() {
            return {
                showSolution: true
            }
        }
    }
</script>
<style>
    #solution {
        cursor: pointer;
    }
    .fade-enter {
        opacity: 0
    }
    .fade-enter-active {
        transition: opacity .5s;
    }

</style>
