# 翻译语音接口
## 请求

1. 请求方式：GET
2. 请求地址：http://fanyi.sogou.com/reventondc/microsoftGetSpeakFile
3. 请求参数：

    参数 | 值 | 类型 | 描述
    --- | --- | --- | ---
    spokenDialect | `zh-CHS` | String |必填
    gender | `female` | String | 选填
    media | `audio/mp3` | String | 选填
    text | `zh-CHS` | String | 必填

4. gender 性别 默认为 `女音`

    性别 | 值
    --- | ---
    男音 | `male`
    女音 | `female`

5. media 格式 默认为 `mp3`

    格式 | 值
    --- | ---
    mp3 | `audio/mp3`
    wav | `audio/wav`

6. `spokenDialect` 语言列表 未标注部分为方言 请只发送标注名称的语种简码

    语种简码 | 名称
    --- | ---
    ar       |  阿拉伯语
    ar-eg    |
    ca       |  加泰隆语
    ca-es    |
    da       |  丹麦语
    da-dk    |
    de       |  德语
    de-de    |
    en       |  英语
    en-au    |
    en-ca    |
    en-gb    |
    en-in    |
    en-us    |
    es       |  西班牙语
    es-es    |
    es-mx    |
    fi       |  芬兰语
    fi-fi    |
    fr       |  法语
    fr-ca    |
    fr-fr    |
    hi       |  印地语
    hi-in    |
    it       |  意大利语
    it-it    |
    ja       |  日语
    ja-jp    |
    ko       |  朝鲜语
    ko-kr    |
    nb-no    |
    nl       |  荷兰语
    nl-nl    |
    no       |  挪威语
    pl       |  波兰语
    pl-pl    |
    pt       |  葡萄牙语
    pt-br    |
    pt-pt    |
    ru       |  俄语
    ru-ru    |
    sv       |  瑞典语
    sv-se    |
    yue      |
    zh-chs   |  中文
    zh-cht   |
    zh-cn    |
    zh-hk    |
    zh-tw    |

## 响应
> 音频流
响应头信息：
```
HTTP/1.1 200 OK
Server: nginx
Date: Wed, 24 May 2017 09:28:47 GMT
Content-Type: audio/mpeg; charset=UTF-8
Content-Length: 5616
Connection: keep-alive
```





