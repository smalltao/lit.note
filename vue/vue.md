#vue.js �ʼ�
ԭ��ַ https://cn.vuejs.org/v2/guide/

#��� 
## ʲô�����
���������չhtmlʵ������װ�����õĴ��룬������Զ���Ԫ�أ�Ϊvue������⹦��
1��ע��
    
    ȫ��ע�����
    �﷨��Vue.component(componentName, opthions) eg:
    Vue.component('my-componentName', {
        //ѡ��
    })
    
    ע��
    Vue.component('my-button', {
        template: '<p>����һ�����</p>'
    })
    
    //ʵ����ʹ��
    new Vue({
        el: '#example'
    })
    
    <div id="example">
        <my-button></my-button>
    </div>
    
    �ֲ�ע�����
    
    var child = {
        template : '<p>����һ�����</p>'
    }
    new Vue({
        el: '#example',
        components: {
            'my-component': child
        }
    })
    
