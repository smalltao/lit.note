import Vue from "vue";
import App from "./App.vue";

Vue.component('thanks', {
    functional: true,
    render: function (createElement, context) {
        let decoratedGift = createElement(context.props.decoration, context.props.gift)
        return createElement('p', ['Dear John, thanks for ', decoratedGift])
    },
    props: {
        gift: String,
        decoration: String
    }
})

new Vue({
    el: '#app',
    render: h => h(App)
})
