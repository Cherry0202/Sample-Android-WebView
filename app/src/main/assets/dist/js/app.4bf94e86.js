(function(e){function t(t){for(var c,a,i=t[0],u=t[1],s=t[2],l=0,f=[];l<i.length;l++)a=i[l],Object.prototype.hasOwnProperty.call(r,a)&&r[a]&&f.push(r[a][0]),r[a]=0;for(c in u)Object.prototype.hasOwnProperty.call(u,c)&&(e[c]=u[c]);d&&d(t);while(f.length)f.shift()();return o.push.apply(o,s||[]),n()}function n(){for(var e,t=0;t<o.length;t++){for(var n=o[t],c=!0,a=1;a<n.length;a++){var i=n[a];0!==r[i]&&(c=!1)}c&&(o.splice(t--,1),e=u(u.s=n[0]))}return e}var c={},a={app:0},r={app:0},o=[];function i(e){return u.p+"js/"+({}[e]||e)+"."+{"chunk-048a835f":"066fc284","chunk-0f8c6ae4":"61a854ce","chunk-2d22c6cf":"fa6326a3","chunk-243238fa":"cab60965","chunk-47649278":"57ea62ee","chunk-5e7c43f4":"f6891ad1","chunk-ca572760":"9440d011","chunk-0af5aa4e":"1773f2ea","chunk-e7b651dc":"e4787e52","chunk-32a68f12":"9aa192e2","chunk-3c0a60cf":"83dd6614","chunk-3e6a33b0":"2b0575ab","chunk-485d7b29":"259828a9","chunk-4edd7822":"985b9c63","chunk-599b5954":"8620156c","chunk-60afbbba":"a9bc54f7","chunk-6885d60b":"1195d8b9","chunk-70e4469c":"cb773836","chunk-73edcf54":"ea96eba0","chunk-a1ea498c":"179aa2fc"}[e]+".js"}function u(t){if(c[t])return c[t].exports;var n=c[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={"chunk-048a835f":1,"chunk-0f8c6ae4":1,"chunk-243238fa":1,"chunk-47649278":1,"chunk-5e7c43f4":1,"chunk-ca572760":1,"chunk-0af5aa4e":1,"chunk-e7b651dc":1,"chunk-32a68f12":1,"chunk-3c0a60cf":1,"chunk-3e6a33b0":1,"chunk-485d7b29":1,"chunk-4edd7822":1,"chunk-599b5954":1,"chunk-60afbbba":1,"chunk-6885d60b":1,"chunk-70e4469c":1,"chunk-73edcf54":1,"chunk-a1ea498c":1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise((function(t,n){for(var c="css/"+({}[e]||e)+"."+{"chunk-048a835f":"89519aa3","chunk-0f8c6ae4":"3b6cce60","chunk-2d22c6cf":"31d6cfe0","chunk-243238fa":"47a3da40","chunk-47649278":"acad43bb","chunk-5e7c43f4":"a3c82450","chunk-ca572760":"836bccf0","chunk-0af5aa4e":"008c5954","chunk-e7b651dc":"79f44d6c","chunk-32a68f12":"37acec1f","chunk-3c0a60cf":"0aeffbba","chunk-3e6a33b0":"e87dcf47","chunk-485d7b29":"0739386f","chunk-4edd7822":"5dc7bc54","chunk-599b5954":"94b9f9c9","chunk-60afbbba":"b8f5c31b","chunk-6885d60b":"7697b7a8","chunk-70e4469c":"d3f2ba8a","chunk-73edcf54":"33693a4f","chunk-a1ea498c":"5ee4eb62"}[e]+".css",r=u.p+c,o=document.getElementsByTagName("link"),i=0;i<o.length;i++){var s=o[i],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===c||l===r))return t()}var f=document.getElementsByTagName("style");for(i=0;i<f.length;i++){s=f[i],l=s.getAttribute("data-href");if(l===c||l===r)return t()}var d=document.createElement("link");d.rel="stylesheet",d.type="text/css",d.onload=t,d.onerror=function(t){var c=t&&t.target&&t.target.src||r,o=new Error("Loading CSS chunk "+e+" failed.\n("+c+")");o.code="CSS_CHUNK_LOAD_FAILED",o.request=c,delete a[e],d.parentNode.removeChild(d),n(o)},d.href=r;var b=document.getElementsByTagName("head")[0];b.appendChild(d)})).then((function(){a[e]=0})));var c=r[e];if(0!==c)if(c)t.push(c[2]);else{var o=new Promise((function(t,n){c=r[e]=[t,n]}));t.push(c[2]=o);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=i(e);var f=new Error;s=function(t){l.onerror=l.onload=null,clearTimeout(d);var n=r[e];if(0!==n){if(n){var c=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src;f.message="Loading chunk "+e+" failed.\n("+c+": "+a+")",f.name="ChunkLoadError",f.type=c,f.request=a,n[1](f)}r[e]=void 0}};var d=setTimeout((function(){s({type:"timeout",target:l})}),12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(t)},u.m=e,u.c=c,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var c in e)u.d(n,c,function(t){return e[t]}.bind(null,c));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="",u.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var f=0;f<s.length;f++)t(s[f]);var d=l;o.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("cd49")},"0418":function(e,t,n){"use strict";var c=n("7a23"),a=Object(c["N"])("data-v-23b057e6");Object(c["y"])("data-v-23b057e6");var r={class:"title"};Object(c["w"])();var o=a((function(e,t,n,a,o,i){var u=Object(c["D"])("BackButton");return Object(c["v"])(),Object(c["f"])("header",{class:"wrap",style:{color:e.color}},[e.isBackButton?(Object(c["v"])(),Object(c["f"])(u,{key:0})):Object(c["g"])("",!0),Object(c["j"])("h1",r,Object(c["F"])(e.title),1)],4)})),i=n("74c9"),u=Object(c["k"])({components:{BackButton:i["a"]},props:{title:{type:String,required:!0},isBackButton:{type:Boolean,default:!0,required:!1},color:{type:String,required:!1,default:"#707070"}}});n("1432");u.render=o,u.__scopeId="data-v-23b057e6";t["a"]=u},"0916":function(e,t,n){},1432:function(e,t,n){"use strict";n("32ef")},1786:function(e,t,n){},2248:function(e,t,n){},"2f5a":function(e,t,n){"use strict";n("7251")},"32ef":function(e,t,n){},"3c8b":function(e,t,n){},"5a5b":function(e,t,n){"use strict";n("3c8b")},"6a87":function(e){e.exports=JSON.parse('{"nav":{"home":"ホーム","search":"検索","course":"受講コース","notice":"お知らせ","mypage":"マイページ"},"top":{"recommend":"あなたへおすすめ","category":"カテゴリ","teacher":"講師"},"search":{"title":"検索","input":"コース、講師を検索","category":"カテゴリを検索","course":"コース","teacher":"講師","no-result":"検索結果は見つかりませんでした"},"result":{"subtitle":"人気のコース"},"course-detail":{"VR-play-btn":"VRで再生","favorite-btn":"お気に入り","lecture":"レクチャー","review":"レビュー","certificate":"技能認定テスト","taking-course":"受講中のコースへ移動"},"teacher-detail":{"title":"講師","course-list":"コース一覧","favorite-btn":"お気に入り","add-btn":"追加済み"},"mypage":{"title":"マイページ","favorite":"お気に入り","taking-course":"受講コース","certificate":"技能認定証","setting":"設定","change-language":"言語選択","device-link":"デバイスのリンク","logout":"ログアウト"},"notice":{"title":"お知らせ","more":"もっと見る","message":"メッセージ","list":"新着のお知らせ"},"take-course-list":{"title":"受講コース","input":"受講しているコースの検索","finished":"完了"},"favorite":{"title":"お気に入り","course":"コース","teacher":"講師"},"notice-message":{"input":"メッセージを入力"},"review":{"title":"コースへのレビュー","rating-msg":"星をタップして評価して下さい","input-title":"タイトル","input-content":"レビュー内容","submit-btn":"レビューを送信する","submit-btn-edit":"レビューを編集する"},"question":{"title":"講師に質問する","input-contents":"質問内容","submit-btn":"質問を送信する"},"language":{"title":"言語選択"},"take-course":{"finish":"完了","question":"講師に質問する","review":"レビューを書く","review-edit":"レビューを編集","lecture":"レクチャー","certificate":"技能認定テスト","submit-btn":"質問を送信する"},"notice-detail":{"title":"メッセージ"},"device-link":{"title":"デバイスのリンク","new-device":"新しいデバイスと接続"},"certificate":{"title":"技能認定証"},"msg":{"no-item":"がありません"},"VR-device":{"connect":"VRデバイスに接続","cancel":"キャンセル","headset":"VRデバイスを装着しましょう！"}}')},7251:function(e,t,n){},"74c9":function(e,t,n){"use strict";var c=n("7a23"),a=Object(c["N"])("data-v-235f8a1a"),r=a((function(e,t,n,a,r,o){var i=Object(c["D"])("font-awesome-icon");return Object(c["v"])(),Object(c["f"])("button",{type:"button",onclick:"history.back()",class:["back-button",{"active-bg":e.activeBg}]},[Object(c["j"])(i,{icon:e.left,class:"icon"},null,8,["icon"])],2)})),o=n("c074"),i=Object(c["k"])({props:{activeBg:{type:Boolean,require:!1}},setup:function(){var e=Object(c["d"])((function(){return o["a"]}));return{left:e}}});n("2f5a");i.render=r,i.__scopeId="data-v-235f8a1a";t["a"]=i},"7d12":function(e,t,n){"use strict";n.d(t,"a",(function(){return i}));var c,a=n("ab42"),r=n("e312"),o=n("6a87");(function(e){e["JA"]="ja",e["EN"]="en"})(c||(c={}));var i=[{value:c.JA,caption:"日本語"},{value:c.EN,caption:"English"}],u=c.JA,s=Object(a["a"])({legacy:!1,locale:u,messages:{en:r,ja:o}});t["b"]=s},9021:function(e,t,n){},"9cdc":function(e,t,n){"use strict";n("c701")},a887:function(e,t,n){"use strict";n("2248")},b407:function(e,t,n){"use strict";n("1786")},c238:function(e,t,n){"use strict";n("9021")},c701:function(e,t,n){},cd49:function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var c=n("7a23"),a={class:"wrap"};function r(e,t,n,r,o,i){var u=Object(c["D"])("router-view"),s=Object(c["D"])("LoadingSpinner");return Object(c["v"])(),Object(c["f"])(c["a"],null,[Object(c["j"])("div",a,[Object(c["j"])(u)]),Object(c["j"])(s,{isLoad:e.state.isLoading},null,8,["isLoad"])],64)}n("9a2a");var o=n("5502"),i=Object(c["N"])("data-v-2830611f"),u=i((function(e,t,n,a,r,o){var i=Object(c["D"])("Circle8");return Object(c["v"])(),Object(c["f"])("div",{class:{loading:e.isLoad}},[e.isLoad?(Object(c["v"])(),Object(c["f"])(i,{key:0})):Object(c["g"])("",!0)],2)})),s=n("d58b"),l=Object(c["k"])({components:{Circle8:s["a"]},props:{isLoad:{type:Boolean,require:!0}}});n("ed4c");l.render=u,l.__scopeId="data-v-2830611f";var f=l,d=Object(c["k"])({name:"App",components:{LoadingSpinner:f},setup:function(){var e=Object(o["b"])(),t=Object(c["A"])(e.state);return e.dispatch("requestGetStudent"),{state:t}}});n("9cdc");d.render=r;var b=d,h=n("9483");Object(h["a"])("".concat("","service-worker.js"),{ready:function(){console.log("App is being served from cache by a service worker.\nFor more details, visit https://goo.gl/AFskqB")},registered:function(){console.log("Service worker has been registered.")},cached:function(){console.log("Content has been cached for offline use.")},updatefound:function(){console.log("New content is downloading.")},updated:function(){console.log("New content is available; please refresh.")},offline:function(){console.log("No internet connection found. App is running in offline mode.")},error:function(e){console.error("Error during service worker registration:",e)}});n("d3b7");var p=n("6c02"),v=Object(c["N"])("data-v-74cca39e");Object(c["y"])("data-v-74cca39e");var j={class:"content-wrap"};Object(c["w"])();var m=v((function(e,t,n,a,r,o){var i=Object(c["D"])("router-view"),u=Object(c["D"])("BottomNavigation");return Object(c["v"])(),Object(c["f"])(c["a"],null,[Object(c["j"])("main",j,[Object(c["j"])(i)]),Object(c["j"])(u)],64)})),g=Object(c["N"])("data-v-9ecab270");Object(c["y"])("data-v-9ecab270");var k={class:"bottom-navigation"},O={class:"navigation-item"},y={class:"navigation-item"},w={class:"navigation-item"},C={class:"navigation-item"},D={class:"navigation-item"};Object(c["w"])();var S=g((function(e,t,n,a,r,o){var i=Object(c["D"])("font-awesome-icon"),u=Object(c["D"])("router-link");return Object(c["v"])(),Object(c["f"])("footer",null,[Object(c["j"])("ul",k,[Object(c["j"])("li",O,[Object(c["j"])(u,{to:"/"},{default:g((function(){return[Object(c["j"])(i,{icon:e.homeIcon,class:"icon"},null,8,["icon"]),Object(c["j"])("p",null,Object(c["F"])(e.$t("nav.home")),1)]})),_:1})]),Object(c["j"])("li",y,[Object(c["j"])(u,{to:"/search"},{default:g((function(){return[Object(c["j"])(i,{icon:e.searchIcon,class:"icon"},null,8,["icon"]),Object(c["j"])("p",null,Object(c["F"])(e.$t("nav.search")),1)]})),_:1})]),Object(c["j"])("li",w,[Object(c["j"])(u,{to:"/take-course-history"},{default:g((function(){return[Object(c["j"])(i,{icon:e.listIcon,class:"icon"},null,8,["icon"]),Object(c["j"])("p",null,Object(c["F"])(e.$t("nav.course")),1)]})),_:1})]),Object(c["j"])("li",C,[Object(c["j"])(u,{to:"/notice"},{default:g((function(){return[Object(c["j"])(i,{icon:e.bellIcon,class:"icon"},null,8,["icon"]),Object(c["j"])("p",null,Object(c["F"])(e.$t("nav.notice")),1)]})),_:1})]),Object(c["j"])("li",D,[Object(c["j"])(u,{to:"/mypage"},{default:g((function(){return[Object(c["j"])(i,{icon:e.userIcon,class:"icon"},null,8,["icon"]),Object(c["j"])("p",null,Object(c["F"])(e.$t("nav.mypage")),1)]})),_:1})])])])})),N=n("c074"),B=n("b702"),_=Object(c["k"])({setup:function(){var e=Object(c["d"])((function(){return N["h"]})),t=Object(c["d"])((function(){return N["r"]})),n=Object(c["d"])((function(){return N["k"]})),a=Object(c["d"])((function(){return B["a"]})),r=Object(c["d"])((function(){return N["v"]}));return{homeIcon:e,searchIcon:t,listIcon:n,bellIcon:a,userIcon:r}}});n("5a5b");_.render=S,_.__scopeId="data-v-9ecab270";var L=_,q=Object(c["k"])({components:{BottomNavigation:L}});n("c238");q.render=m,q.__scopeId="data-v-74cca39e";var I=q,T=Object(c["N"])("data-v-1ee78a6e");Object(c["y"])("data-v-1ee78a6e");var A={class:"content-wrap"};Object(c["w"])();var R=T((function(e,t,n,a,r,o){var i=Object(c["D"])("router-view");return Object(c["v"])(),Object(c["f"])("main",A,[Object(c["j"])(i)])})),F=Object(c["k"])({});n("a887");F.render=R,F.__scopeId="data-v-1ee78a6e";var P=F,E=Object(c["N"])("data-v-8928c4f4");Object(c["y"])("data-v-8928c4f4");var x={class:"content-wrap"},M={class:"select-list"},V={class:"select-list-navigation"},H={class:"select-list-item"},J={class:"select-list-item"};Object(c["w"])();var $=E((function(e,t,n,a,r,o){var i=Object(c["D"])("Header"),u=Object(c["D"])("router-link"),s=Object(c["D"])("router-view"),l=Object(c["D"])("BottomNavigation");return Object(c["v"])(),Object(c["f"])(c["a"],null,[Object(c["j"])("main",x,[Object(c["j"])(i,{title:e.$t("favorite.title")},null,8,["title"]),Object(c["j"])("div",M,[Object(c["j"])("ul",V,[Object(c["j"])("li",H,[Object(c["j"])(u,{to:"/favorite"},{default:E((function(){return[Object(c["j"])("p",null,Object(c["F"])(e.$t("favorite.course")),1)]})),_:1})]),Object(c["j"])("li",J,[Object(c["j"])(u,{to:"/favorite/teacher"},{default:E((function(){return[Object(c["j"])("p",null,Object(c["F"])(e.$t("favorite.teacher")),1)]})),_:1})])])]),Object(c["j"])(s)]),Object(c["j"])(l)],64)})),U=n("0418"),Q=Object(c["k"])({components:{BottomNavigation:L,Header:U["a"]}});n("b407");Q.render=$,Q.__scopeId="data-v-8928c4f4";var G=Q,K=[{path:"/",component:I,children:[{path:"",name:"Home",component:function(){return n.e("chunk-4edd7822").then(n.bind(null,"bb51"))}},{path:"/search",name:"Search",component:function(){return n.e("chunk-0f8c6ae4").then(n.bind(null,"2d3b"))}},{path:"/result-list",name:"ResultList",component:function(){return n.e("chunk-32a68f12").then(n.bind(null,"54dc"))}},{path:"/course-detail/:id",name:"CourseDetail",component:function(){return Promise.all([n.e("chunk-2d22c6cf"),n.e("chunk-ca572760"),n.e("chunk-e7b651dc")]).then(n.bind(null,"9ca6"))}},{path:"/teacher-detail/:id",name:"TeacherDetail",component:function(){return n.e("chunk-73edcf54").then(n.bind(null,"a804"))}},{path:"/mypage",name:"Mypage",component:function(){return Promise.all([n.e("chunk-2d22c6cf"),n.e("chunk-47649278")]).then(n.bind(null,"bd34"))}},{path:"/change-language",name:"ChangeLanguage",component:function(){return n.e("chunk-6885d60b").then(n.bind(null,"9d13"))}},{path:"/device-link",name:"DeviceLink",component:function(){return Promise.all([n.e("chunk-2d22c6cf"),n.e("chunk-5e7c43f4")]).then(n.bind(null,"e325"))}},{path:"/take-course-history",name:"TakeCourseHistory",component:function(){return n.e("chunk-3c0a60cf").then(n.bind(null,"926c"))}},{path:"/notice",name:"Notice",component:function(){return n.e("chunk-599b5954").then(n.bind(null,"c9d4"))}},{path:"/notice-detail",name:"NoticeDetail",component:function(){return n.e("chunk-60afbbba").then(n.bind(null,"19fa"))}},{path:"/take-course-detail",name:"TakeCourseDetail",component:function(){return Promise.all([n.e("chunk-2d22c6cf"),n.e("chunk-ca572760"),n.e("chunk-0af5aa4e")]).then(n.bind(null,"9fbb"))}},{path:"/admitted-skill-list",name:"AdmittedSkillList",component:function(){return Promise.all([n.e("chunk-2d22c6cf"),n.e("chunk-243238fa")]).then(n.bind(null,"cc36"))}},{path:"/question-form",name:"QuestionForm",component:function(){return n.e("chunk-048a835f").then(n.bind(null,"43fc"))}},{path:"/review-form",name:"ReviewForm",component:function(){return n.e("chunk-a1ea498c").then(n.bind(null,"ac0a"))}}]},{path:"/favorite",component:G,children:[{path:"",name:"FavoriteCourse",component:function(){return n.e("chunk-485d7b29").then(n.bind(null,"2a65"))}},{path:"teacher",name:"FavoriteTeacher",component:function(){return n.e("chunk-3e6a33b0").then(n.bind(null,"a6aa"))}}]},{path:"/notice-message/:id",component:P,children:[{path:"",name:"NoticeMessage",component:function(){return n.e("chunk-70e4469c").then(n.bind(null,"7ecb"))}}]}],W=Object(p["a"])({history:Object(p["b"])(),routes:K,scrollBehavior:function(e,t,n){return n?new Promise((function(e){setTimeout((function(){e(n)}))})):{left:0,top:0}}}),Y=W,z=(n("96cf"),n("1da1")),X=n("1840"),Z=n("8785"),ee=n("00b9");function te(){var e=Object(Z["a"])(["\n  query students {\n    Students {\n      name\n      photoUrl\n      uid\n    }\n  }\n"]);return te=function(){return e},e}var ne=Object(ee["gql"])(te()),ce=ne,ae=function(e,t){return JSON.parse(Android.threadMaker(e,t))},re=ae,oe={isLoading:!1,currentUser:void 0,connectingBluetoothDevice:{deviceName:null,macAddress:null}},ie=Object(o["a"])({state:oe,mutations:{setLoading:function(e,t){e.isLoading=t},setCurrentUser:function(e,t){e.currentUser=t},setConnectingBluetoothDevice:function(e,t){e.connectingBluetoothDevice.deviceName=t.deviceName,e.connectingBluetoothDevice.macAddress=t.macAddress}},actions:{requestGetStudent:function(e){return Object(z["a"])(regeneratorRuntime.mark((function t(){var n,c,a,r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return n=e.commit,t.next=3,Object(X["c"])(ce);case 3:c=t.sent,a=c.result,r=Object(X["d"])(a,null,(function(e){return e.Students[0]})),n("setCurrentUser",r);case 7:case"end":return t.stop()}}),t)})))()},submitBTDeviceData:function(e,t){return Object(z["a"])(regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,re(oe.connectingBluetoothDevice.macAddress,t.videoData);case 2:return n=e.sent,e.abrupt("return",n.result);case 4:case"end":return e.stop()}}),e)})))()},requestSetConnectingBluetoothDevice:function(e,t){return Object(z["a"])(regeneratorRuntime.mark((function n(){var c;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return c=e.commit,n.next=3,c("setConnectingBluetoothDevice",t.device);case 3:case"end":return n.stop()}}),n)})))()}},modules:{}}),ue=n("7d12"),se=n("ad3d"),le=n("d2f5"),fe=n("1fcf"),de=new ee["HttpLink"]({uri:"https://h456-takumi.herokuapp.com/v1/graphql"}),be=new le["a"]({uri:"wss://h456-takumi.herokuapp.com/v1/graphql",options:{reconnect:!0}}),he=Object(ee["split"])((function(e){var t=e.query,n=Object(fe["p"])(t);return"OperationDefinition"===n.kind&&"subscription"===n.operation}),be,de),pe=new ee["ApolloClient"]({link:he,cache:new ee["InMemoryCache"],connectToDevTools:!0});Object(c["e"])({setup:function(){Object(c["x"])(X["a"],pe)},render:function(){return Object(c["m"])(b)}}).use(ie).use(Y).use(ue["b"]).component("font-awesome-icon",se["a"]).mount("#app")},e312:function(e){e.exports=JSON.parse('{"nav":{"home":"Home","search":"Search","course":"Course","notice":"Notice","mypage":"My page"},"top":{"recommend":"Recommended courses","category":"Categories","teacher":"Instructors"},"search":{"title":"Search","input":"course name, category, instructor\'s name, etc.","category":"Categories","course":"Courses","teacher":"Instructors","no-result":"Nothing found..."},"result":{"subtitle":"Popular courses"},"course-detail":{"VR-play-btn":"Play in VR","favorite-btn":"Favorite","lecture":"Lecture","review":"Review","certificate":"Skill Certificate Tests","taking-course":"Go to course"},"teacher-detail":{"title":"Instructor","course-list":"Course List","favorite-btn":"Favorite","add-btn":"Added"},"mypage":{"title":"My page","favorite":"Favorite","taking-course":"Taking course","certificate":"Certificates","setting":"Setting","change-language":"Change Language","device-link":"Devices","logout":"Sign Out"},"notice":{"title":"Notifications","more":"more","message":"Messages","list":"Earlier"},"take-course-list":{"title":"Taking Courses","input":"Search for taking courses","finished":"complete"},"favorite":{"title":"Favorites","course":"Courses","teacher":"Instructor"},"notice-message":{"input":"Enter your message"},"review":{"title":"Reviews","rating-msg":"Tap a star to rate it.","input-title":"Title","input-content":"Type in your question contents","submit-btn":"Send","submit-btn-edit":"Edit"},"question":{"title":"Question Form","input-contents":"Type in your question here","submit-btn":"Send Your Question"},"language":{"title":"Language Settings"},"take-course":{"finish":"finished","question":"Question","review":"Write a Review","review-edit":"Edit a Review","lecture":"Lecture","certificate":"Skill Certificate Tests","submit-btn":"Send"},"notice-detail":{"title":"Messages"},"device-link":{"title":"Device","new-device":"Connect with new devices"},"certificate":{"title":"Certificates"},"msg":{"no-item":" are empty."},"VR-device":{"connect":"Connect to VR devices","cancel":"Cancel","headset":"Please put on the Headset!"}}')},ed4c:function(e,t,n){"use strict";n("0916")}});
//# sourceMappingURL=app.4bf94e86.js.map