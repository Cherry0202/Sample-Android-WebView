(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-599b5954"],{2640:function(t,e,c){"use strict";c("90e8")},"5c69":function(t,e,c){t.exports=c.p+"img/TAKUMI_Logo_Vertical.3e6c6d18.png"},"643c":function(t,e,c){"use strict";c("a679")},"78c2":function(t,e,c){"use strict";var n=function(t,e){return t.length<=e?t:t.substring(0,e)+"..."};e["a"]=n},"90e8":function(t,e,c){},a679:function(t,e,c){},aab5:function(t,e,c){"use strict";var n=c("7a23"),a=Object(n["N"])("data-v-3a4c3af1");Object(n["y"])("data-v-3a4c3af1");var i={class:"wrap"},o={class:"circle"},r={class:"msg"};Object(n["w"])();var s=a((function(t,e,c,a,s,u){return Object(n["v"])(),Object(n["f"])("div",i,[Object(n["j"])("div",o,[Object(n["C"])(t.$slots,"default",{class:"icon"})]),Object(n["j"])("h3",r,Object(n["F"])(t.text)+Object(n["F"])(t.$t("msg.no-item")),1)])})),u=Object(n["k"])({props:{text:{type:String,required:!0}}});c("2640");u.render=s,u.__scopeId="data-v-3a4c3af1";e["a"]=u},b0c0:function(t,e,c){var n=c("83ab"),a=c("9bf2").f,i=Function.prototype,o=i.toString,r=/^\s*function ([^ (]*)/,s="name";n&&!(s in i)&&a(i,s,{configurable:!0,get:function(){try{return o.call(this).match(r)[1]}catch(t){return""}}})},c9d4:function(t,e,c){"use strict";c.r(e);c("b0c0");var n=c("7a23"),a=c("5c69"),i=c.n(a),o=Object(n["N"])("data-v-30b4ba2a");Object(n["y"])("data-v-30b4ba2a");var r={key:1,class:"wrap"},s={class:"message-top"},u={class:"headline"},b={class:"message-wrap"},j={class:"message-tag"},l={class:"message-teacher-name"},O={class:"notice-heading headline"},d=Object(n["j"])("img",{src:i.a,alt:"",class:"icon"},null,-1),f={class:"notice-message"};Object(n["w"])();var v=o((function(t,e,c,a,i,v){var m,g,p=Object(n["D"])("Header"),h=Object(n["D"])("font-awesome-icon"),w=Object(n["D"])("NoItem"),_=Object(n["D"])("router-link");return Object(n["v"])(),Object(n["f"])(n["a"],null,[Object(n["j"])(p,{title:t.$t("notice.title"),"is-back-button":!1},null,8,["title"]),(null===(m=t.chatRoom)||void 0===m?void 0:m.length)||(null===(g=t.notices)||void 0===g?void 0:g.length)?(Object(n["v"])(),Object(n["f"])("div",r,[Object(n["j"])("div",s,[Object(n["j"])("h3",u,Object(n["F"])(t.$t("notice.message")),1),Object(n["j"])(_,{to:"/notice-detail"},{default:o((function(){return[Object(n["i"])(Object(n["F"])(t.$t("notice.more")),1)]})),_:1})]),Object(n["j"])("div",b,[(Object(n["v"])(!0),Object(n["f"])(n["a"],null,Object(n["B"])(t.chatRoom,(function(e,c){return Object(n["v"])(),Object(n["f"])(_,{to:{name:"NoticeMessage",params:{id:e.id}},key:c,class:"message-link"},{default:o((function(){return[Object(n["j"])("img",{src:e.Course.image,alt:"",class:"message-image"},null,8,["src"]),Object(n["j"])("span",j,Object(n["F"])(e.Course.Category.name),1),Object(n["j"])("p",l,Object(n["F"])(t.truncate(e.Course.Instructor.name,6)),1)]})),_:2},1032,["to"])})),128))]),Object(n["j"])("h3",O,Object(n["F"])(t.$t("notice.list")),1),(Object(n["v"])(!0),Object(n["f"])(n["a"],null,Object(n["B"])(t.notices,(function(e,c){return Object(n["v"])(),Object(n["f"])("div",{key:c,class:"notice-item"},[d,Object(n["j"])("p",f,Object(n["F"])(e.title),1),Object(n["j"])(h,{icon:t.arrowRight,class:"arrow-icon"},null,8,["icon"])])})),128))])):(Object(n["v"])(),Object(n["f"])(w,{key:0,text:t.$t("notice.title")},{default:o((function(){return[Object(n["j"])(h,{icon:t.bellIcon},null,8,["icon"])]})),_:1},8,["text"]))],64)})),m=c("1840"),g=c("8785"),p=c("00b9");function h(){var t=Object(g["a"])(["\n  query Notice($student_id: uuid) {\n    ChatRooms(where: { student_id: { _eq: $student_id } }) {\n      id\n      Course {\n        image\n        Instructor {\n          name\n        }\n        Category {\n          name\n        }\n      }\n    }\n    Notices(order_by: { created_at: desc }) {\n      title\n    }\n  }\n"]);return h=function(){return t},t}var w=Object(p["gql"])(h()),_=w,y=c("0418"),k=c("aab5"),C=c("c074"),F=c("5502"),I=c("78c2"),$=c("b702"),N=Object(n["k"])({components:{Header:y["a"],NoItem:k["a"]},setup:function(){var t,e=Object(F["b"])(),c=Object(m["c"])(_,{student_id:null===(t=e.state.currentUser)||void 0===t?void 0:t.uid}),a=c.result,i=Object(m["d"])(a,null,(function(t){return t.ChatRooms})),o=Object(m["d"])(a,null,(function(t){return t.Notices})),r=Object(n["d"])((function(){return C["d"]})),s=Object(n["d"])((function(){return $["a"]}));return{chatRoom:i,notices:o,bellIcon:s,arrowRight:r,truncate:I["a"]}}});c("643c");N.render=v,N.__scopeId="data-v-30b4ba2a";e["default"]=N}}]);
//# sourceMappingURL=chunk-599b5954.8620156c.js.map