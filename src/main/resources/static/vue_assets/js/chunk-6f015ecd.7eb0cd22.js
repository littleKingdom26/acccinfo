(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f015ecd"],{5631:function(t,e,s){"use strict";var a=function(t,e){var s=e._c;return s("span",e._g(e._b({staticClass:"material-design-icon chevron-right-circle-icon",class:[e.data.class,e.data.staticClass],attrs:{"aria-hidden":e.props.decorative,"aria-label":e.props.title,role:"img"}},"span",e.data.attrs,!1),e.listeners),[s("svg",{staticClass:"material-design-icon__svg",attrs:{fill:e.props.fillColor,width:e.props.size,height:e.props.size,viewBox:"0 0 24 24"}},[s("path",{attrs:{d:"M22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2A10,10 0 0,1 22,12M10,18L16,12L10,6L8.6,7.4L13.2,12L8.6,16.6L10,18Z"}},[s("title",[e._v(e._s(e.props.title))])])])])},i=[],l=(s("a9e3"),{name:"ChevronRightCircleIcon",props:{title:{type:String,default:"Chevron Right Circle icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),n=l,r=s("2877"),o=Object(r["a"])(n,a,i,!0,null,null,null);e["a"]=o.exports},"88c2":function(t,e,s){},ac3c:function(t,e,s){"use strict";s("88c2")},ce58:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"home"},[s("Header"),t._m(0),t._m(1),s("div",{staticClass:"session mb-5",attrs:{id:"league_information"}},[s("h1",{staticClass:"title"},[t._v(" LEAGUE INFORMATION ")]),s("h3",{staticClass:"subTitle"},[t._v(" FINAL LAP League Information ")]),s("hr",{staticClass:"yellow"}),t.posterType.length?s("div",{staticClass:"tabs"},t._l(t.posterType,(function(e,a){return s("b-button",{key:a,staticClass:"roundedBtn",class:{active:e.key==t.slideType},attrs:{variant:"outline-secondary"},on:{click:function(s){t.slideType=e.key}}},[t._v(t._s(e.value))])})),1):t._e(),t.leagueSlideList.length?s("Slider",{attrs:{animation:"fade",autoplay:!1,height:"auto",stopOnHover:!0},model:{value:t.sliderValue,callback:function(e){t.sliderValue=e},expression:"sliderValue"}},t._l(t.leagueSlideList,(function(e,a){return s("SliderItem",{key:a,staticClass:"leagueCarousel",style:t.slideStyle},[s("img",{attrs:{src:e.imgPath}})])})),1):t._e(),s("div",{staticClass:"tabs lastBtnWrap Staatliches"},[s("b-row",[s("b-col",[s("b-button",{staticClass:"lastBtn",attrs:{to:"/notice/131"}},[s("span",[t._v("JOIN OUR LEAGUE")]),s("mdiChevronRightCircle",{attrs:{color:"#ffffff"}})],1)],1),s("b-col",[s("b-button",{staticClass:"lastBtn",attrs:{to:"/notice/93"}},[s("span",[t._v("PENALTY RULES")]),s("mdiChevronRightCircle",{attrs:{color:"#ffffff"}})],1)],1)],1),s("b-row",{staticClass:"mt-4"},[s("b-col",[s("b-button",{staticClass:"lastBtn",attrs:{to:"/notice/64"}},[s("span",[t._v("RACE MANNER")]),s("mdiChevronRightCircle",{attrs:{color:"#ffffff"}})],1)],1),s("b-col",[s("b-button",{staticClass:"lastBtn",attrs:{to:"/notice/82"}},[s("span",[t._v("BEGINNER GUIDE")]),s("mdiChevronRightCircle",{attrs:{color:"#ffffff"}})],1)],1)],1)],1)],1),s("div",{staticClass:"text-center mb-5"},[s("b-button",{staticClass:"logo",attrs:{variant:"link",to:"/"}})],1)],1)},i=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"session mb-5"},[s("h1",{staticClass:"title"},[t._v(" LEAGUE CALENDAR ")]),s("h3",{staticClass:"subTitle"},[t._v(" ACC KOREA League Calendar ")]),s("hr",{staticClass:"yellow"})])},function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"session notice Staatliches text-center mb-5"},[s("iframe",{staticStyle:{"border-width":"0"},attrs:{src:"https://calendar.google.com/calendar/embed?height=600&wkst=1&bgcolor=%23616161&ctz=Asia%2FSeoul&src=ZDVwdDN0NTBrOHRjdTRwbzNtcGpkaW0zYWtAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ&color=%23F4511E&showTitle=0&showCalendars=0&showPrint=0&showNav=1",width:"1140",height:"600",frameborder:"0",scrolling:"no"}})])}],l=s("0418"),n=s("5631"),r=s("3b80"),o={name:"Home",components:{Header:l["a"],Slider:r["a"],SliderItem:r["b"],mdiChevronRightCircle:n["a"]},data:function(){return{slideStyle:{backgroundPosition:"center",backgroundSize:"contain",backgroundRepeat:"no-repeat",left:"0",right:"0",margin:"0 auto"},leagueSlideList:[],posterType:[],slideType:"sub",sliderValue:0}},created:function(){this._getContent(),this._getPosterType()},watch:{slideType:function(){this._getContent()}},methods:{_getContent:function(){var t=this;this.$axios.get("/api/main/poster/".concat(this.slideType),{withCredentials:!1}).then((function(e){t.leagueSlideList=e.data.data}))},_getPosterType:function(){var t=this;this.$axios.get("/api/common/posterType",{withCredentials:!1}).then((function(e){t.posterType=e.data.data.sort((function(t,e){return t.key>e.key?-1:t.key>e.key?1:0}))}))}}},c=o,d=(s("ac3c"),s("2877")),u=Object(d["a"])(c,a,i,!1,null,"3f1eaea2",null);e["default"]=u.exports}}]);
//# sourceMappingURL=chunk-6f015ecd.7eb0cd22.js.map