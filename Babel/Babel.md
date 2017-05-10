# [Babel �ٷ��̳�](https://babeljs.io/learn-es2015/)
# [��һ�岩��](http://www.ruanyifeng.com/blog/2016/01/babel.html)
ʹ��babel ʹ��ES6�﷨��д����js
    
    // ת��ǰ
    input.map(item => item + 1);
    
    // ת���
    input.map(function (item) {
      return item + 1;
    });
    
## �����ļ�
    
    Babel�������ļ���.babelrc���������Ŀ�ĸ�Ŀ¼�¡�ʹ��Babel�ĵ�һ����������������ļ���
    ���ļ���������ת�����Ͳ����������ʽ���¡�
    
    {
      "presets": [],
      "plugins": []
    }
    presets�ֶ��趨ת����򣬹ٷ��ṩ���µĹ��򼯣�����Ը�����Ҫ��װ��
    
    # ES2015ת�����
    $ npm install --save-dev babel-preset-es2015
    
    # reactת�����
    $ npm install --save-dev babel-preset-react
    
    # ES7��ͬ�׶��﷨�᰸��ת����򣨹���4���׶Σ���ѡװһ��
    $ npm install --save-dev babel-preset-stage-0
    $ npm install --save-dev babel-preset-stage-1
    $ npm install --save-dev babel-preset-stage-2
    $ npm install --save-dev babel-preset-stage-3
    
    {
        "presets": [
          "es2015",
          "react",
          "stage-2"
        ],
        "plugins": []
      }
      
## ������ת�빤��
    
    Babel�ṩbabel-cli���ߣ�����������ת�롣
    ���İ�װ�������¡�
    
    $ npm install --global babel-cli
    �����÷����¡�

    # ת�����������׼���
    $ babel example.js
    
    # ת����д��һ���ļ�
    # --out-file �� -o ����ָ������ļ�
    $ babel example.js --out-file compiled.js
    # ����
    $ babel example.js -o compiled.js
    
    # ����Ŀ¼ת��
    # --out-dir �� -d ����ָ�����Ŀ¼
    $ babel src --out-dir lib
    # ����
    $ babel src -d lib
    
    # -s ��������source map�ļ�
    $ babel src -d lib -s
    
    Ȼ�󣬸�дpackage.json��
    {
      // ...
      "devDependencies": {
        "babel-cli": "^6.0.0"
      },
      "scripts": {
        "build": "babel src -d lib"
      },
    }
    
    ת���ʱ�򣬾�ִ����������
    
    $ npm run build
    
## babel-node

    babel-cli�����Դ�һ��babel-node����ṩһ��֧��ES6��REPL��������֧��Node��REPL���������й��ܣ����ҿ���ֱ������ES6���롣
    �����õ�����װ��������babel-cliһ��װ��Ȼ��ִ��babel-node�ͽ���PEPL������
    
    $ babel-node
    > (x => x * 2)(1)
    2
    babel-node�������ֱ������ES6�ű���������Ĵ������ű��ļ�es6.js��Ȼ��ֱ�����С�
    
    $ babel-node es6.js
    2
    babel-nodeҲ���԰�װ����Ŀ�С�
    
    $ npm install --save-dev babel-cli
    Ȼ�󣬸�дpackage.json��
    
    {
      "scripts": {
        "script-name": "babel-node script.js"
      }
    }
    ��������У�ʹ��babel-node���node������script.js����Ͳ������κ�ת�봦��
    
## babel-register

    babel-registerģ���дrequire���Ϊ������һ�����ӡ��˺�ÿ��ʹ��require����.js��.jsx��.es��.es6��׺�����ļ����ͻ�����Babel����ת�롣
    
    $ npm install --save-dev babel-register
    ʹ��ʱ���������ȼ���babel-register��
    
    require("babel-register");
    require("./index.js");
    Ȼ�󣬾Ͳ���Ҫ�ֶ���index.jsת���ˡ�
    ��Ҫע����ǣ�babel-registerֻ���require������ص��ļ�ת�룬������Ե�ǰ�ļ�ת�롣���⣬��������ʵʱת�룬����ֻ�ʺ��ڿ�������ʹ�á�
    
## babel-core

    ���ĳЩ������Ҫ����Babel��API����ת�룬��Ҫʹ��babel-coreģ�顣
    ��װ�������¡�
    
    $ npm install babel-core --save
    Ȼ������Ŀ�оͿ��Ե���babel-core��
    
    var babel = require('babel-core');
    
    // �ַ���ת��
    babel.transform('code();', options);
    // => { code, map, ast }
    
    // �ļ�ת�루�첽��
    babel.transformFile('filename.js', options, function(err, result) {
      result; // => { code, map, ast }
    });
    
    // �ļ�ת�루ͬ����
    babel.transformFileSync('filename.js', options);
    // => { code, map, ast }
    
    // Babel ASTת��
    babel.transformFromAst(ast, code, options);
    // => { code, map, ast }
    ���ö���options�����Բο��ٷ��ĵ�http://babeljs.io/docs/usage/options/��
    ������һ�����ӡ�
    
    var es6Code = 'let x = n => n + 1';
    var es5Code = require('babel-core')
      .transform(es6Code, {
        presets: ['es2015']
      })
      .code;
    // '"use strict";\n\nvar x = function x(n) {\n  return n + 1;\n};'
    ��������У�transform�����ĵ�һ��������һ���ַ�������ʾ��Ҫת����ES6���룬�ڶ���������ת�������ö���
    
## babel-polyfill