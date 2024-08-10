/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
//

window.addEventListener('DOMContentLoaded', event => {
    sideBar();
    /* 그룹 소 제목 : main */
    sideMenu('pagesCollapseActive');
    sideMenu('corporateOrganizationPages');

    /* 그룹 소 제목 : Schedule */
    sideMenu('vacationPages');
    sideMenu('commutePages');

    /* Communication */
    sideMenu('boardPages');
    sideMenu('mailPages');
    sideMenu('videoChattingPage');

    /* 그룹 소 제목 : Material Flow Management */
    sideMenu('materialFlowMenu');



    randomGreetings();
});


function sideBar() {
    // Toggle the side navigation 사이드 메뉴 토글기능
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // 새로 고침 사이에 사이드바 토글을 유지
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
}

function sideMenu(id) {
    const collapseElement = document.getElementById(id);
    const triggerElement = document.querySelector('[data-bs-toggle="collapse"][data-bs-target="#' + id + '"]');

    // Local Storage 에서 축소 상태를 확인
    if (localStorage.getItem(id) === 'true') {
        collapseElement.classList.add('show');
        triggerElement.classList.remove('collapsed');
        triggerElement.setAttribute('aria-expanded', 'true');
    } else {
        collapseElement.classList.remove('show');
        triggerElement.classList.add('collapsed');
        triggerElement.setAttribute('aria-expanded', 'false');
    }

    // 접힌 상태가 변경될 때 Local Storage 업데이트하는 이벤트 리스너를 추가
    collapseElement.addEventListener('shown.bs.collapse', () => {
        localStorage.setItem(id, 'true');
    });

    collapseElement.addEventListener('hidden.bs.collapse', () => {
        localStorage.setItem(id, 'false');
    });
}

function randomGreetings() {
    const greeting = greetings[new Date().getMonth()];
    let size = greeting.length;
    document.getElementById('sideFooterGreeting').innerText = greeting[Math.floor(Math.random() * size)];
}

const greetings = [
    [
        "★한 해가 저물고 새로운 해를 맞이하게 되었습니다. 좋았던 기억은 남기고 안 좋았던 기억은 저물은\n" +
        " 해에 태워 보내십시오! 새해에는 즐겁고 행복한 소식만 가득하시길 바랍니다.\n",
        "★행복한 1월 항상 즐거운 마음으로 할기 차게 마무리 하시길 바라요~!\n" +
        " 더 크게 성장하시고 풍요로운 새해 여십시오~!\n",
        "★새해의 첫 시작처럼 끝마무리도 잘 하시리가 믿습니다. 첫 시작 좋은 하루 되십시오~!\n" +
        " 새해 복 많이 받으시고 바라는 바 모두 이루는 한 해가 되시길 바랍니다.^^\n",
        "★첫 시작 언제나 그 자리에서 빛을 발하는 별처럼 굳은 결심과 함께 한 해를 여십시오~!\n" +
        " 올 한해 건강하시고 새로운 출발! 힘차게 달려 봅시다!\n",
        "★새해 더 크게 성장하시고 뜻하는 일 다 이루시길 기원합니다.\n" +
        " 소망하시는 모든 일과 가내 축복이 깃드는 한 해가 되십시오\n",
        "★새해를 맞이하여 그동안 보내주신 성원에 깊은 감사를 드립니다. 2017년 행복한 일들만\n" +
        " 가득하시길 바랍니다.\n",
        "★새해에는 소망하는 일 모두 이루시길 바라며 늘 변함없는 관심 부탁드립니다.\n",
        "★지난해 소망하시는 일들 모두 이루셨는지요? 000 님에게 좋은 가르침을 받았다는 것에 늘 감사하게\n" +
        " 생각합니다. 소중한 가르침 본받아 좋은 사람으로 성장하겠습니다. 감사합니다.\n",
        "★설날의 풍요로움은 우리 민족의 명절 설을 맞이하여 가정에 평안과 행운이 깃들면서 기쁜 일 가득한\n" +
        " 한 해가 되시길 바라겠습니다.\n",
        "★ 민족의 대명절 설을 맞이하여 가정에는 화목을 더하시고 2017년 크게 펼칠 꿈을 위해 힐링하시는\n" +
        " 시간 보내시기 바라겠습니다.\n",
        "★곳곳에 웃음소리가 끊이질 않는 행복한 설날입니다. 웃으면 복이 온다는 옛 속담처럼 항상\n" +
        " 웃으시면서 올 한해 운수 대통하시기 바라겠습니다.\n",
        "★가족 친지와 기쁨을 나누는 설날입니다. 오늘과 같은 화목과 평화가 나날이 이어지는 한 해가\n" +
        " 되시길 바라겠습니다."
    ],
    [
        "★올해도 벌써 한 달이 훌쩍 지났네요 2월의 시작입니다. 건강 유의하시고 즐거운 하루 되십시오!\n",
        "★입춘이라고는 하지만 아직은 추운 날씨네요 감기 조심하십시오~!!\n",
        "★마음은 벌써 봄으로 가 있는듯한데 아직 겨울은 2월 한 달을 버티고 있습니다. 남은 겨울 건강히\n" +
        " 지내십시오!\n",
        "★새해 첫 시작이 엊그제 같은데 벌써 구정 설날이 성큼 다가왔습니다. 구정이 지나야 진짜\n" +
        " 새해라는 말이 있지요! 올 한 해도 복 많이 받으시고 소망하는 모든 일 이루시길 기원합니다."
    ],
    [
        "★쌀쌀한 봄바람이 아침저녁으로 옷깃을 여미게 합니다. 감기 조심하십시오!\n",
        "★이른 봄의 내음이 조금씩 느껴지기 시작하는 것 같습니다. 활짝 핀 꽁과 나무가 더울 기다려지는\n" +
        " 그런 달입니다. 따뜻한 봄날을 기다리며 오늘도 힘찬 하루 되세요!\n",
        "★하루하루 지날 때마다 조금씩 낮이 길어지는 것이 느껴지는 것 같습니다. 조용히 다가오는 봄을\n" +
        " 기다리며 기분 좋은 시간 되시길 바랍니다.\n",
        "★아침저녁 신선한 공기가 조금은 따뜻해졌습니다. 활기찰 하루 되시고요 환절기 건강 조심하십시오",
    ],
    [
        "★햇살이 제법 따뜻한 봄날입니다. 스치는 바람에도 봄 냄새가 느껴집니다.\n" +
        " 사는 게 바쁘지만 따뜻한 햇살을 받으며 향긋한 내음을 느끼는 4월이 되셨으면 합니다.\n",
        "★봄의 따스함이 울리 곁에 늘 머물렀으면 좋겠다 싶은 햇살 좋은 날입니다.\n" +
        " 햇살만큼이나 따뜻한 미소가 주위 사람을 행복하게 만든답니다.\n",
        "★4월은 초록으로 물듦이 제일 예쁜 것 같습니다. 활기찬 하루 시작하십시오~"
    ],
    [
        "★초록이 점점 짙어가는 5월이 되었습니다. 빠르게만 지나가는 시간 속에 남은 봄을 만끽할 수 있는 하루가 되었으면 좋겠습니다.\n",
        "★5월에는 감사의 마음을 전할 곳도 많고 초록의 짙은 만큼이나 지출도 큰 달입니다. ^^\n",
        "★일 년 중 지출이 큰 달을 뽑으라면 5월 일 것 같습니다. 해야 하나 말아야 하나 고민되실 땐 하는 게 맞는\n" +
        " 겁니다. 즐거운 마음으로 정을 나누십시오\n",
        "★신록들이 나날이 푸름을 더해가는 5월입니다. 나무마다 가자마다 살이 오르려 한껏 기지개를\n" +
        " 펴면서 하루가 다르게 제 키를 키워 갑니다.\n",
        "★부부의 날이 언제인지 아시죠 바로 5월 21일입니다. 사랑하는 두 분이 더욱 깊은 행복 나누는\n" +
        " 하루 되길 바랍니다.\n",
        "★5월이 장미의 순수한 열정으로 붉게 타오르고 있습니다. 우리 일도 나날이 크기를 키워가는 열정의\n" +
        " 5월이기를 바래 봅니다.\n",
        "★화창한 날들이지만 때때로 더위도 느껴지는 요즘입니다. 슬슬 여름을 준비할 때가 되지 않았나\n" +
        " 싶습니다.\n",
        "★계절의 여왕 5월의 짙푸른 신록이 몸과 마음을 상쾌하게 해줍니다. 여왕처럼 아름답고 매력 있는\n" +
        " 모습으로 행복한 5월 보내시기 바랍니다."
    ],
    [
        "★화창했던 5월도 가고 이제는 여름이 시작되는 6월입니다. 날씨가 더워지고 지치기 쉬운 계절입니다.  \n" +
        " 몸 관리에 소홀하지 마시고 충분히 휴식하시면서 즐거운 나날들 보내시길 바랍니다.~!\n",
        "★아카시아 향기가 솔솔 풍겼던 5월도 가고 6월의 한주가 시작되었습니다. 1년의 절반이 되었네요\n" +
        " 잊고 지냈던 소중한 분들에게 안부라도 여쭙는 6월이었으면 좋겠습니다.\n",
        "★제법 날씨도 더워지고 있습니다. 작년 여름 더위만 생각한다면 끔찍하지만 이제 산과 들, 바다로 \n" +
        " 후련히 떠날 수 있다는 기대감에 즐거운 달입니다."
    ],
    [
        "★여름이 시작됐던 6월도 어느새 지나고 7월의 시작입니다.\n" +
        " 장마가 있다고 하는데 건강 챙기시고 몸조심하십시오~!\n",
        "★더운 여름 더위에 지치시더라도 항상 즐거운 마음으로 행복한 7월 되시길 바랍니다.^^\n",
        "★7월의 시작! 본격적 더위가 시작되면 짜증도 함께 올라가는데요 항상 미소 지으며 행복한 하루 보내십시오"
    ],
    [
        "★어느덧 7월이 지나고 벌써 8월의 시작입니다. 무더운 여름 이제 한 달 남짓 남았는데요^^\n" +
        " 작년 더위 생각하시면서 8월의 열기를 달래십시오^^\n",
        "★본격적 휴가 시즌 8월이 왔습니다. 여행 계획도 미리미리 준비하시고 즐거운 휴가 되십시오!\n",
        "★8월입니다.~~! 본격적 휴가 시즌 힐링도 좋지만 안전 질서 잊지 마시고 행복한 휴가 되십시오.\n",
        "★무더운 날씨 수분 보충 충분히 하시고 건강한 여름 나십시오!\n",
        "★연휴가 있어 좋은 8월 잠시나마 모든 걸 잊고 떠나십시오! 누가 그랬더라 인생은 쏘아 올린 화살 같다고~~!"
    ],
    [
        "★결실을 준비하는 계절 가을의 시작 9월입니다. 그동안 준비해오시던 결실을 맺으시고! 풍성한\n" +
        " 9월 되세요^^\n",
        "★아침저녁 바람이 어느새 서늘해지는 9월입니다. 환절기 감기 조심하시고 건강하고 행복한\n" +
        " 9월 되세요!\n",
        "★선조들의 지혜가 요즘 따라 더 빛을 발휘합니다. 절기가 어쩜 이렇게 잘 맞는지 엊그제는 숨 막히던\n" +
        " 더위는 온데간데없고 이제 제법 쌀쌀하네요 환절기 건강 유의하십시오!\n",
        "★가을은 추수의 계절입니다. 00 님도 풍성한 가을과 같이 큰 결실 수확하십시오!"
    ],
    [
        "★오색으로 물든 산과 들이 완연한 가을입니다. 옷을 갈아입는 자연처럼 모두 다 새롭게 다시 시작하는\n" +
        " 10월 되십시오!\n",
        "★10월은 가장 아름다운 계절입니다. 아름다운 계절 오색이 물든 자연과 같이 가장 아름다운\n" +
        " 축복 속으로 당신도 함께 들어가 보시길 바랍니다.\n",
        "★단풍이 곱게 물드는 계절입니다. 마음이 통하는 벗과 함께 추억을 나누고 싶어지는 10월입니다.\n",
        "★청명한 가을 하늘 홍옆으로 가득 찬 세상! 가을이 아니면 줄 수 없는 선물입니다.\n",
        "★한 방울 이슬 속에서 찾은 행복한 가을  가을은 축복의 계절이 아닐 수 없습니다.\n",
        "★오곡백과로 풍성함을 뽐내는 들판을 보니 가을의 정취를 물씬 느끼게 됩니다.\n",
        "★코스모스 길을 따라 고추잠자리를 잡던 추억이 떠올려지는 계절입니다."
    ],
    [
        "★어느덧 한 해의 마지막이 한 달여 남았습니다. 한 해의 마지막을 준비하는 한 달이 되셨으면 합니다.\n",
        "★쌀쌀한 바람이 새삼 옷 깃을 여미게 하는 11월 고즈넉한 늦가을의 풍경에선 이제 겨울의 냉기가\n",
        " 느껴지네요 환절기 감기 조심하시고 건강하고 행복한 11월 되십시오!"
    ],
    [
        "★12월 한 해의 마지막 달입니다. 올 한 해도 고생하셨습니다." +
        " 내년에는 더욱 풍성하고 행복만 가득한 한 해가 되시길 바랍니다.\n",
        "★즐거운 성탄절 따뜻한 연말 보내시고 희망찬 새해 맞이하세요!\n" ,
        "★한 해 동안 보내주신 관심과 은혜에 감사드립니다.\n",
        "★집안 두루 평안하시고 복이 깃드시기를 바랍니다. 새해 복 많이 받으십시오!\n",
        "★올 한해 흘린 땀방울만큼 새해에는 더 큰 복이 찾아오기를 기원합니다.\n",
        "★올해도 고생 많으셨습니다. 덕분에 올해 별 탈 없이 지냈습니다." +
        " 따뜻한 연말 보내시고 새해에는 원 하시는 모든 것이 이루어지기 바랍니다.\n",
        "★한 해 동안 도움 주심 점 감사드리며 새해에는 더욱 건강하시고 뜻깊은 한 해가 되시길 기원합니다."
    ]
]