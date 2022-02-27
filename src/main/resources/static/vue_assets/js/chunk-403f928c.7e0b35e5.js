(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-403f928c"],{"4e37":function(t,e,s){"use strict";s("b816")},"5b4a":function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"home"},[s("Header"),t._m(0),t._m(1),s("div",{staticClass:"session notice JeojuGthic text-left mb-5"},[s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_applicant"}},[t._v("심의 신청자")]),s("b-form-input",{staticClass:"custom_input",attrs:{id:"input_applicant",placeholder:"NICKNAME",trim:""},model:{value:t.applicant,callback:function(e){t.applicant=e},expression:"applicant"}})],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_candidate"}},[t._v("심의 대상자")]),s("b-form-input",{staticClass:"custom_input",attrs:{id:"input_candidate",placeholder:"NICKNAME",trim:""},model:{value:t.candidate,callback:function(e){t.candidate=e},expression:"candidate"}})],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_accident"}},[t._v("항의 범주")]),t.faults.length?s("b-dropdown",{staticClass:"selectDropdown",attrs:{id:"input_accident",text:"","no-flip":"","no-caret":""},scopedSlots:t._u([{key:"button-content",fn:function(){return[s("span",[t._v(t._s(t.fault_selection.value))]),s("mdiChevronDownCircle",{attrs:{color:"#8a8a8a"}})]},proxy:!0}],null,!1,2773345624)},t._l(t.faults,(function(e){return s("b-dropdown-item",{key:e.key,attrs:{href:"#"},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.onClickFaultDropdown(e)}}},[t._v(t._s(e.value))])})),1):t._e()],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_race_event"}},[t._v("레이스 이벤트")]),s("b-form-input",{staticClass:"custom_input",attrs:{id:"input_race_event",placeholder:"EX: 21년 6월 27일 일요리그",trim:""},model:{value:t.race_event,callback:function(e){t.race_event=e},expression:"race_event"}})],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_session"}},[t._v("세션 단계")]),s("b-form-input",{staticClass:"custom_input",attrs:{id:"input_session",placeholder:"EX: 프로리그 / 레이스",trim:""},model:{value:t.session,callback:function(e){t.session=e},expression:"session"}})],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_replay_time"}},[t._v("리플레이 시간")]),s("b-form-input",{staticClass:"custom_input",attrs:{id:"input_replay_time",placeholder:"EX: 0랩 00분 00초",trim:""},model:{value:t.replay_time,callback:function(e){t.replay_time=e},expression:"replay_time"}})],1),s("div",{staticClass:"input_group",attrs:{role:"group"}},[s("label",{attrs:{for:"input_description"}},[t._v("사건 설명")]),s("b-form-textarea",{staticClass:"custom_input",attrs:{id:"input_description",placeholder:"사건 설명",rows:"6","max-rows":"6",trim:""},model:{value:t.description,callback:function(e){t.description=e},expression:"description"}})],1),s("div",{staticClass:"text-right"},[s("b-button",{staticClass:"lastBtn",on:{click:function(e){return t._postContent()}}},[s("span",[t._v("REGISTER")])])],1)]),s("div",{staticClass:"text-center mb-5"},[s("b-button",{staticClass:"logo",attrs:{variant:"link",to:"/"}})],1)],1)},i=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"session mb-5"},[s("h1",{staticClass:"title"},[t._v(" PENALTY ")]),s("h3",{staticClass:"subTitle"},[t._v(" Review Request Board ")]),s("hr",{staticClass:"yellow"})])},function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"session notice JeojuGthic mb-5"},[s("h2",[t._v("심의 신청")]),s("p",[t._v(" 경기 내 부여된 페널티 이외의 상황에 대해 심의 신청을 할 수 있습니다."),s("br"),t._v("심의 신청기간은 레이스 종료 후 24시간 이내까지 신청해주시기 바랍니다. ")])])}],n=(s("a4d3"),s("e01a"),s("0418")),o=s("d9ad"),r={name:"Home",components:{Header:n["a"],mdiChevronDownCircle:o["a"]},data:function(){return{applicant:"",candidate:"",race_event:"",session:"",replay_time:"",description:"",fault_selection:{value:"01. 비매너 주행",key:"unManner"},faults:[]}},created:function(){},mounted:function(){this._getComplaintsCode()},methods:{_getComplaintsCode:function(){var t=this;this.$axios.get("/api/common/complaintsCode",{withCredentials:!1}).then((function(e){t.faults=e.data.data}))},_postContent:function(){var t=this;if(!this.fault_selection.key|!this.description|!this.race_event|!this.applicant|!this.candidate|!this.replay_time|!this.session)this.$bvModal.msgBoxOk("입력란이 비어있습니다. 확인해주세요.",{title:"확인",size:"sm",buttonSize:"sm",okVariant:"danger",headerClass:"p-2 border-bottom-0",footerClass:"p-2 border-top-0",centered:!0});else{var e={complaints:this.fault_selection.key,description:this.description,event:this.race_event,regId:this.applicant,reviewTarget:this.candidate,replayTime:this.replay_time,sessionDivision:this.session};this.$axios.post("/api/reviewRequest",e,{withCredentials:!1}).then((function(e){e.data.success?(t.fault_selection={value:"01. 비매너 주행",key:"unManner"},t.description="",t.race_event="",t.applicant="",t.candidate="",t.replay_time="",t.session="",t.$bvModal.msgBoxOk("정상 등록 되었습니다.",{title:"확인",size:"sm",buttonSize:"sm",okVariant:"success",headerClass:"p-2 border-bottom-0",footerClass:"p-2 border-top-0",centered:!0})):t.$bvModal.msgBoxOk("시스템 오류입니다. 다시 시도해 주세요.",{title:"확인",size:"sm",buttonSize:"sm",okVariant:"danger",headerClass:"p-2 border-bottom-0",footerClass:"p-2 border-top-0",centered:!0})}))}},onClickFaultDropdown:function(t){this.fault_selection=t}}},l=r,c=(s("4e37"),s("2877")),p=Object(c["a"])(l,a,i,!1,null,"383cb290",null);e["default"]=p.exports},b816:function(t,e,s){},d9ad:function(t,e,s){"use strict";var a=function(t,e){var s=e._c;return s("span",e._g(e._b({staticClass:"material-design-icon chevron-down-circle-icon",class:[e.data.class,e.data.staticClass],attrs:{"aria-hidden":e.props.decorative,"aria-label":e.props.title,role:"img"}},"span",e.data.attrs,!1),e.listeners),[s("svg",{staticClass:"material-design-icon__svg",attrs:{fill:e.props.fillColor,width:e.props.size,height:e.props.size,viewBox:"0 0 24 24"}},[s("path",{attrs:{d:"M22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2A10,10 0 0,1 22,12M6,10L12,16L18,10L16.6,8.6L12,13.2L7.4,8.6L6,10Z"}},[s("title",[e._v(e._s(e.props.title))])])])])},i=[],n=(s("a9e3"),{name:"ChevronDownCircleIcon",props:{title:{type:String,default:"Chevron Down Circle icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),o=n,r=s("2877"),l=Object(r["a"])(o,a,i,!0,null,null,null);e["a"]=l.exports}}]);
//# sourceMappingURL=chunk-403f928c.7e0b35e5.js.map