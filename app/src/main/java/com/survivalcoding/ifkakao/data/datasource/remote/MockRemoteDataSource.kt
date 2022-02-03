package com.survivalcoding.ifkakao.data.datasource.remote

import com.google.gson.Gson
import com.survivalcoding.ifkakao.domain.model.IfKakaoData
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository

class MockRemoteDataSource : SessionRemoteRepository {
    override suspend fun getIfKakaoData() = MockData.getIfKakaoData()
}

object MockData {
    val data: IfKakaoData = Gson().fromJson(mockData, IfKakaoData::class.java)
    fun getIfKakaoData(): IfKakaoData = data
}

private val mockData = """
    {
  "success": true,
  "code": 0,
  "data": [
    {
      "idx": 20,
      "title": "이용자의 소중한 일상을 지키는 카카오톡",
      "content": "이용자의 소중한 일상을 지키려는 카카오톡의 노력을 소개합니다.\n\n카카오톡의 대화와 사진, 동영상뿐만 아니라 아이디와 비밀번호,\n대용량 파일도 안전하게 보관하는 '톡서랍' 업그레이드,\n디지털 세상에서 내가 나임을 증명할 수 있는 '신분증',\n환경 보호와 개인 정보 보호 두 마리 토끼를 잡는 '톡명함',\n그리고 '오픈채팅'에서의 이용자 보호 조치들에 대한 이야기를 나눕니다.",
      "contentTag": "#카카오톡  #톡서랍업그레이드  #신분증  #톡명함  #오픈채팅이용자보호",
      "field": "서비스",
      "company": "카카오",
      "linkList": {
        "FILE": [],
        "IMAGE": [],
        "WEB_URL": [],
        "VIDEO": [
          {
            "idx": 3683,
            "contentsIdx": 20,
            "type": "VIDEO",
            "fileSize": 0,
            "url": "https://tv.kakao.com/embed/player/cliplink/423791694",
            "description": "09:41",
            "mainYn": "N"
          }
        ],
        "PC_THUMBNAIL": [],
        "MO_THUMBNAIL": [],
        "TALK_THUMBNAIL": [],
        "SPEAKER_PROFILE": [
          {
            "idx": 3682,
            "contentsIdx": 20,
            "type": "SPEAKER_PROFILE",
            "fileSize": 109555,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403129640",
            "description": "If Kakao_CPO_1022.png",
            "mainYn": "N"
          }
        ],
        "PC_MAIN_IMAGE": [
          {
            "idx": 3677,
            "contentsIdx": 20,
            "type": "PC_MAIN_IMAGE",
            "fileSize": 170699,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403318460",
            "description": "***1027_A세션_썸네일_42.png",
            "mainYn": "N"
          }
        ],
        "MO_MAIN_IMAGE": [
          {
            "idx": 3678,
            "contentsIdx": 20,
            "type": "MO_MAIN_IMAGE",
            "fileSize": 87866,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403306708",
            "description": "***1027_A세션_썸네일_61.png",
            "mainYn": "N"
          }
        ],
        "PC_IMAGE": [
          {
            "idx": 3679,
            "contentsIdx": 20,
            "type": "PC_IMAGE",
            "fileSize": 68188,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403330102",
            "description": "***1027_A세션_썸네일_2.png",
            "mainYn": "Y"
          }
        ],
        "MO_IMAGE": [
          {
            "idx": 3680,
            "contentsIdx": 20,
            "type": "MO_IMAGE",
            "fileSize": 20136,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403338353",
            "description": "***1027_A세션_썸네일_22.png",
            "mainYn": "N"
          }
        ],
        "SHARE_IMAGE": [
          {
            "idx": 3681,
            "contentsIdx": 20,
            "type": "SHARE_IMAGE",
            "fileSize": 68188,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403350046",
            "description": "***1027_A세션_썸네일_2.png",
            "mainYn": "N"
          }
        ],
        "PC_SPOTLIGHT": [
          {
            "idx": 3675,
            "contentsIdx": 20,
            "type": "PC_SPOTLIGHT",
            "fileSize": 38760,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403280606",
            "description": "***1027_A세션_썸네일_79.png",
            "mainYn": "N"
          }
        ],
        "MO_SPOTLIGHT": [
          {
            "idx": 3676,
            "contentsIdx": 20,
            "type": "MO_SPOTLIGHT",
            "fileSize": 24294,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403263604",
            "description": "***1027_A세션_썸네일_97.png",
            "mainYn": "N"
          }
        ]
      },
      "relationList": {
        "CLASSIFICATION": [
          "플랫폼",
          "디지털자산"
        ],
        "TECH_CLASSIFICATION": [],
        "MAIN_EXPOSURE_DAY": [
          "1Day"
        ]
      },
      "contentsSpeakerList": [
        {
          "idx": 1273,
          "contentsIdx": 20,
          "nameKo": "김택수",
          "nameEn": "felix",
          "company": "카카오",
          "occupation": "CPO"
        }
      ],
      "favoriteYn": "N",
      "newCountentsYn": "N",
      "updateCountentsYn": "N",
      "companyName": "카카오",
      "speakerName": "felix김택수",
      "speakerLoginYn": "N",
      "reservationUTC": 1636992000000,
      "reservationYn": "Y",
      "videoYn": "Y"
    },
    {
      "idx": 93,
      "createdUserIdx": 18,
      "createdDateTime": "2021-10-28 17:13:36",
      "lastModifiedUserIdx": 18,
      "lastModifiedDateTime": "2021-11-10 15:14:11",
      "categoryIdx": 7,
      "title": "고성능 블록체인 지갑 서비스 Klip 개발기",
      "content": "바닥부터 설계한 웹 기반 블록체인 지갑 서비스 개발 노하우 공유",
      "contentTag": "#golang #react #webserver #블록체인",
      "spotlightYn": "N",
      "field": "기술",
      "sessionType": "B Type",
      "qnaStartDay": "3",
      "qnaStartTime": "14:00",
      "qnaEndTime": "15:00",
      "commentYn": "Y",
      "company": "그라운드X",
      "reservationDate": "20211117",
      "reservationTime": "1000",
      "linkList": {
        "FILE": [
          {
            "idx": 3827,
            "contentsIdx": 93,
            "type": "FILE",
            "fileSize": 775185,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636524850648",
            "description": "고성능 블록체인 지갑 서비스 Klip 개발기_바닥부터 설계한 웹서비스 개발 노하우 공유.pdf",
            "mainYn": "N"
          }
        ],
        "IMAGE": [],
        "WEB_URL": [],
        "VIDEO": [
          {
            "idx": 3826,
            "contentsIdx": 93,
            "type": "VIDEO",
            "fileSize": 0,
            "url": "https://tv.kakao.com/embed/player/cliplink/423595377",
            "description": "17:47",
            "mainYn": "N"
          }
        ],
        "PC_THUMBNAIL": [],
        "MO_THUMBNAIL": [],
        "TALK_THUMBNAIL": [],
        "SPEAKER_PROFILE": [
          {
            "idx": 3825,
            "contentsIdx": 93,
            "type": "SPEAKER_PROFILE",
            "fileSize": 100136,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418463159",
            "description": "0915_그라운드X_Albert.Nah.png",
            "mainYn": "N"
          }
        ],
        "PC_MAIN_IMAGE": [],
        "MO_MAIN_IMAGE": [],
        "PC_IMAGE": [
          {
            "idx": 3822,
            "contentsIdx": 93,
            "type": "PC_IMAGE",
            "fileSize": 35627,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418494827",
            "description": "0915_그라운드X_Albert.Nah_560.png",
            "mainYn": "Y"
          }
        ],
        "MO_IMAGE": [
          {
            "idx": 3823,
            "contentsIdx": 93,
            "type": "MO_IMAGE",
            "fileSize": 22392,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418487513",
            "description": "0915_그라운드X_Albert.Nah_236.png",
            "mainYn": "N"
          }
        ],
        "SHARE_IMAGE": [
          {
            "idx": 3824,
            "contentsIdx": 93,
            "type": "SHARE_IMAGE",
            "fileSize": 35627,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418479514",
            "description": "0915_그라운드X_Albert.Nah_560.png",
            "mainYn": "N"
          }
        ],
        "PC_SPOTLIGHT": [],
        "MO_SPOTLIGHT": []
      },
      "relationList": {
        "CLASSIFICATION": [],
        "TECH_CLASSIFICATION": [
          "블록체인"
        ],
        "MAIN_EXPOSURE_DAY": []
      },
      "contentsSpeakerList": [
        {
          "idx": 1298,
          "contentsIdx": 93,
          "nameKo": "나정호",
          "nameEn": "albert",
          "company": "그라운드X",
          "occupation": "블록체인지갑개발팀",
          "loginEmail": "jeongho.nah@kakao.com"
        }
      ],
      "favoriteYn": "N",
      "newCountentsYn": "N",
      "updateCountentsYn": "N",
      "companyName": "그라운드X",
      "speakerName": "albert나정호",
      "speakerLoginYn": "N",
      "reservationUTC": 1637078400000,
      "reservationYn": "Y",
      "videoYn": "Y"
    },
    {
      "idx": 94,
      "createdUserIdx": 17,
      "createdDateTime": "2021-10-28 17:15:26",
      "lastModifiedUserIdx": 17,
      "lastModifiedDateTime": "2021-11-10 15:41:30",
      "categoryIdx": 7,
      "title": "Distributed tracing 도입기 - Microservice architecture에서의 debugging",
      "content": "쿠버네티스 위에 서비스를 런칭한 뒤 가시성을 확보하기 위해 Observability 요소 중 하나인 분산 트레이싱을 도입하고 사용했던 사례를 공유",
      "contentTag": "#Kubernetes #Cloud #Observability #Tracing #ServiceMesh",
      "spotlightYn": "N",
      "field": "기술",
      "sessionType": "B Type",
      "qnaStartDay": "2",
      "qnaStartTime": "11:00",
      "qnaEndTime": "12:00",
      "commentYn": "Y",
      "company": "그라운드X",
      "reservationDate": "20211117",
      "reservationTime": "1000",
      "linkList": {
        "FILE": [
          {
            "idx": 4183,
            "contentsIdx": 94,
            "type": "FILE",
            "fileSize": 3334814,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636526486542",
            "description": "Distributed tracing 도입기_Microservice architecture 에서의 debugging.pdf",
            "mainYn": "N"
          }
        ],
        "IMAGE": [],
        "WEB_URL": [],
        "VIDEO": [
          {
            "idx": 4182,
            "contentsIdx": 94,
            "type": "VIDEO",
            "fileSize": 0,
            "url": "https://tv.kakao.com/embed/player/cliplink/423595401",
            "description": "15:53",
            "mainYn": "N"
          }
        ],
        "PC_THUMBNAIL": [],
        "MO_THUMBNAIL": [],
        "TALK_THUMBNAIL": [],
        "SPEAKER_PROFILE": [
          {
            "idx": 4181,
            "contentsIdx": 94,
            "type": "SPEAKER_PROFILE",
            "fileSize": 112669,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418958611",
            "description": "0908_그라운드X_Jade.Jung.png",
            "mainYn": "N"
          }
        ],
        "PC_MAIN_IMAGE": [],
        "MO_MAIN_IMAGE": [],
        "PC_IMAGE": [
          {
            "idx": 4178,
            "contentsIdx": 94,
            "type": "PC_IMAGE",
            "fileSize": 47424,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635419026241",
            "description": "0908_그라운드X_Jade.Jung_560.png",
            "mainYn": "Y"
          }
        ],
        "MO_IMAGE": [
          {
            "idx": 4179,
            "contentsIdx": 94,
            "type": "MO_IMAGE",
            "fileSize": 26020,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635419043480",
            "description": "0908_그라운드X_Jade.Jung_236.png",
            "mainYn": "N"
          }
        ],
        "SHARE_IMAGE": [
          {
            "idx": 4180,
            "contentsIdx": 94,
            "type": "SHARE_IMAGE",
            "fileSize": 47424,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635419055448",
            "description": "0908_그라운드X_Jade.Jung_560.png",
            "mainYn": "N"
          }
        ],
        "PC_SPOTLIGHT": [],
        "MO_SPOTLIGHT": []
      },
      "relationList": {
        "CLASSIFICATION": [],
        "TECH_CLASSIFICATION": [
          "인프라/DevOps",
          "클라우드"
        ],
        "MAIN_EXPOSURE_DAY": []
      },
      "contentsSpeakerList": [
        {
          "idx": 1369,
          "contentsIdx": 94,
          "nameKo": "정초아",
          "nameEn": "jade",
          "company": "그라운드X",
          "occupation": "DevOps팀",
          "loginEmail": "whoisxx1@gmail.com"
        }
      ],
      "favoriteYn": "N",
      "newCountentsYn": "N",
      "updateCountentsYn": "N",
      "companyName": "그라운드X",
      "speakerName": "jade정초아",
      "speakerLoginYn": "N",
      "reservationUTC": 1637078400000,
      "reservationYn": "Y",
      "videoYn": "Y"
    },
    {
      "idx": 95,
      "createdUserIdx": 18,
      "createdDateTime": "2021-10-28 17:16:34",
      "lastModifiedUserIdx": 16,
      "lastModifiedDateTime": "2021-11-16 09:41:23",
      "categoryIdx": 7,
      "title": "Klaytn을 이용하여 NFT 활용하기: 발행부터 판매까지",
      "content": "요새 화두가 되고 있는 NFT를 클레이튼에서 어떻게 발행/전송/매매할 수 있는지 살펴보려고 합니다. 또한 미술시장에서 논의되고 있는 추급권을 스마트 컨트랙트를 이용하여 어떻게 구현할 수 있는지 설명합니다. 스마트 컨트랙트를 이용하면 매매 즉시 추급권 및 거래수수료 정산을 마칠 수 있습니다. 이어서 추급권을 포함한 NFT 매매 전체 시나리오를 설명드리고, 남은 향후 과제는 어떤 것들이 있는지 말씀드리고자 합니다.\n\n\uD83D\uDCAC 이 세션은 소셜 오디오 플랫폼 음mm에서 연사자와 직접 만나는 After Talk이 마련되어 있습니다.\n\n\uD83C\uDF99 블록체인, NFT 어디까지 알아보셨어요?\nᄂ 일시 : 11월 22일(월) 17:00 ~ 17:30\nᄂ 링크 : https://www.mm.xyz/event/d321a7a0-d34e-44d3-8bcd-658290cef28d\n\n잠깐! 음mm은 모바일 전용 App입니다.\n- Android 사용자 음mm 다운로드  https://play.google.com/store/apps/details?id=com.kakao.mmoa \n- iOS 사용자 음mm 다운로드  https://apps.apple.com/kr/app/id1567177871",
      "contentTag": "#NFT #NFT마켓 #추급권 #Royalty #스마트컨트랙트",
      "spotlightYn": "N",
      "field": "기술",
      "sessionType": "B Type",
      "qnaStartDay": "3",
      "qnaStartTime": "16:00",
      "qnaEndTime": "17:00",
      "commentYn": "Y",
      "company": "그라운드X",
      "reservationDate": "20211117",
      "reservationTime": "1000",
      "linkList": {
        "FILE": [
          {
            "idx": 4629,
            "contentsIdx": 95,
            "type": "FILE",
            "fileSize": 2628001,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636526317627",
            "description": "클레이튼에서 NFT 활용하기_발행부터 판매까지.pdf",
            "mainYn": "N"
          }
        ],
        "IMAGE": [],
        "WEB_URL": [],
        "VIDEO": [
          {
            "idx": 4628,
            "contentsIdx": 95,
            "type": "VIDEO",
            "fileSize": 0,
            "url": "https://tv.kakao.com/embed/player/cliplink/423595395",
            "description": "21:09",
            "mainYn": "N"
          }
        ],
        "PC_THUMBNAIL": [],
        "MO_THUMBNAIL": [],
        "TALK_THUMBNAIL": [],
        "SPEAKER_PROFILE": [
          {
            "idx": 4627,
            "contentsIdx": 95,
            "type": "SPEAKER_PROFILE",
            "fileSize": 109679,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418400584",
            "description": "0831_그라운드X_Colin.Kim.png",
            "mainYn": "N"
          }
        ],
        "PC_MAIN_IMAGE": [],
        "MO_MAIN_IMAGE": [],
        "PC_IMAGE": [
          {
            "idx": 4624,
            "contentsIdx": 95,
            "type": "PC_IMAGE",
            "fileSize": 39969,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418430983",
            "description": "0831_그라운드X_Colin.Kim_560.png",
            "mainYn": "Y"
          }
        ],
        "MO_IMAGE": [
          {
            "idx": 4625,
            "contentsIdx": 95,
            "type": "MO_IMAGE",
            "fileSize": 23432,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418424371",
            "description": "0831_그라운드X_Colin.Kim_236.png",
            "mainYn": "N"
          }
        ],
        "SHARE_IMAGE": [
          {
            "idx": 4626,
            "contentsIdx": 95,
            "type": "SHARE_IMAGE",
            "fileSize": 39969,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418416486",
            "description": "0831_그라운드X_Colin.Kim_560.png",
            "mainYn": "N"
          }
        ],
        "PC_SPOTLIGHT": [],
        "MO_SPOTLIGHT": []
      },
      "relationList": {
        "CLASSIFICATION": [],
        "TECH_CLASSIFICATION": [
          "블록체인"
        ],
        "MAIN_EXPOSURE_DAY": []
      },
      "contentsSpeakerList": [
        {
          "idx": 1461,
          "contentsIdx": 95,
          "nameKo": "김정현",
          "nameEn": "colin",
          "company": "그라운드X",
          "occupation": "블록체인지갑개발팀",
          "loginEmail": "colin.kim@kakao.com"
        }
      ],
      "favoriteYn": "N",
      "newCountentsYn": "N",
      "updateCountentsYn": "N",
      "companyName": "그라운드X",
      "speakerName": "colin김정현",
      "speakerLoginYn": "N",
      "reservationUTC": 1637078400000,
      "reservationYn": "Y",
      "videoYn": "Y"
    },
    {
      "idx": 123,
      "createdUserIdx": 16,
      "createdDateTime": "2021-10-28 19:36:30",
      "lastModifiedUserIdx": 16,
      "lastModifiedDateTime": "2021-11-05 17:45:43",
      "categoryIdx": 8,
      "title": "카카오 애자일 상담소 (3일차)",
      "content": "카카오에서 일하는 방식이 궁금하신가요? \n제품, 조직 관점에서 일하는 방식의 변화를 이끌어 가는 과정에서 고민이 있으신가요?\n아니면, 카카오의 애자일코치는 어떤 사람들인지 궁금하신가요?\n\n그렇다면 카카오 애자일 상담소를 찾아주세요.\nif(kakao) 2021 콘퍼런스가 진행되는 3일동안 매일 오후 2시부터 3시까지 카카오의 애자일코치 세명과 함께 이야기를 나눌 수 있습니다.\n궁금하신 내용은 미리 사전 질문 링크를 통해 남겨주시면 라이브 진행시 답변을 드릴 예정입니다.\n물론, 라이브 진행시 손\uD83D\uDC4B을 들어 직접 음성으로 대화를 나눌 수도 있어요.\n\n3일차 : 1~2일차 주요 내용 요약 및 자유주제\n- 사전질문 : https://app.sli.do/event/7sokjytj\n\n* 카카오 애자일상담소는 소셜 오디오 플랫폼 ‘음mm’ 에서 라이브로 진행됩니다.\n- Android 사용자 음mm 다운로드 \nhttps://play.google.com/store/apps/details?id=com.kakao.mmoa\n- iOS 사용자 음mm 다운로드 \nhttps://apps.apple.com/kr/app/id1567177871",
      "contentTag": "#애자일 #애자일코칭 #성장 #협업 #커뮤니케이션 #함께성장하기 #일하는방식",
      "spotlightYn": "N",
      "field": "기술",
      "sessionType": "Live",
      "commentYn": "N",
      "company": "카카오",
      "reservationDate": "20211118",
      "reservationTime": "1000",
      "linkList": {
        "FILE": [],
        "IMAGE": [],
        "WEB_URL": [],
        "IDEO": [
          {
            "idx": 3351,
            "contentsIdx": 123,
            "type": "VIDEO",
            "fileSize": 0,
            "url": "https://www.mm.xyz/event/8293328d-6eb0-49e7-9682-afef26ecd90a",
            "mainYn": "N"
          }
        ],
        "PC_THUMBNAIL": [],
        "MO_THUMBNAIL": [],
        "TALK_THUMBNAIL": [],
        "SPEAKER_PROFILE": [
          {
            "idx": 3348,
            "contentsIdx": 123,
            "type": "SPEAKER_PROFILE",
            "fileSize": 94883,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635744838850",
            "description": "0914_카카오_Bella.Coach.png",
            "mainYn": "N"
          },
          {
            "idx": 3349,
            "contentsIdx": 123,
            "type": "SPEAKER_PROFILE",
            "fileSize": 103173,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635744842681",
            "description": "프로필_benedict.png",
            "mainYn": "N"
          },
          {
            "idx": 3350,
            "contentsIdx": 123,
            "type": "SPEAKER_PROFILE",
            "fileSize": 119564,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635744847318",
            "description": "프로필_jazz.png",
            "mainYn": "N"
          }
        ],
        "PC_MAIN_IMAGE": [],
        "MO_MAIN_IMAGE": [],
        "PC_IMAGE": [
          {
            "idx": 3345,
            "contentsIdx": 123,
            "type": "PC_IMAGE",
            "fileSize": 456488,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417366233",
            "description": "day3_상세페이지_pc.png",
            "mainYn": "Y"
          }
        ],
        "MO_IMAGE": [
          {
            "idx": 3346,
            "contentsIdx": 123,
            "type": "MO_IMAGE",
            "fileSize": 40349,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417380197",
            "description": "day3_상세페이지_mo.png",
            "mainYn": "N"
          }
        ],
        "SHARE_IMAGE": [
          {
            "idx": 3347,
            "contentsIdx": 123,
            "type": "SHARE_IMAGE",
            "fileSize": 456488,
            "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417370786",
            "description": "day3_상세페이지_pc.png",
            "mainYn": "N"
          }
        ],
        "PC_SPOTLIGHT": [],
        "MO_SPOTLIGHT": []
      },
      "relationList": {
        "CLASSIFICATION": [],
        "TECH_CLASSIFICATION": [
          "개발문화",
          "기타"
        ],
        "MAIN_EXPOSURE_DAY": []
      },
      "contentsSpeakerList": [
        {
          "idx": 1188,
          "contentsIdx": 123,
          "nameKo": "백미진",
          "nameEn": "bella",
          "company": "카카오",
          "occupation": "애자일코치"
        },
        {
          "idx": 1189,
          "contentsIdx": 123,
          "nameKo": "이호정",
          "nameEn": "benedict",
          "company": "카카오",
          "occupation": "애자일코치"
        },
        {
          "idx": 1190,
          "contentsIdx": 123,
          "nameKo": "김지훈",
          "nameEn": "jazz",
          "company": "카카오",
          "occupation": "애자일코치"
        }
      ],
      "favoriteYn": "N",
      "newCountentsYn": "N",
      "updateCountentsYn": "N",
      "companyName": "카카오",
      "speakerName": "bella백미진",
      "speakerLoginYn": "N",
      "reservationUTC": 1637164800000,
      "reservationYn": "Y",
      "videoYn": "Y"
    }
  ],
  "count": 120,
  "errorMessage": null
}
"""
