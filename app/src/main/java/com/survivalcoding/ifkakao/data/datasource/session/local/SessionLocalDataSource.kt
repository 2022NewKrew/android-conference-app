package com.survivalcoding.ifkakao.data.datasource.session.local

import com.google.gson.Gson
import com.survivalcoding.ifkakao.data.datasource.session.SessionDataSource
import com.survivalcoding.ifkakao.data.datasource.session.SessionResponse
import com.survivalcoding.ifkakao.domain.model.*
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor() : SessionDataSource {

    private val json = """
        {"success":true,"code":0,"data":
         [{
              "idx": 20,
              "createdUserIdx": 16,
              "createdDateTime": "2021-10-28 15:38:53",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-10 10:39:47",
              "categoryIdx": 6,
              "title": "이용자의 소중한 일상을 지키는 카카오톡",
              "content": "이용자의 소중한 일상을 지키려는 카카오톡의 노력을 소개합니다.\n\n카카오톡의 대화와 사진, 동영상뿐만 아니라 아이디와 비밀번호,\n대용량 파일도 안전하게 보관하는 '톡서랍' 업그레이드,\n디지털 세상에서 내가 나임을 증명할 수 있는 '신분증',\n환경 보호와 개인 정보 보호 두 마리 토끼를 잡는 '톡명함',\n그리고 '오픈채팅'에서의 이용자 보호 조치들에 대한 이야기를 나눕니다.",
              "contentTag": "#카카오톡  #톡서랍업그레이드  #신분증  #톡명함  #오픈채팅이용자보호",
              "spotlightYn": "Y",
              "field": "서비스",
              "sessionType": "A Type",
              "commentYn": "Y",
              "company": "카카오",
              "reservationDate": "20211116",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
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
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
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
                "TECH_CLASSIFICATION": [
                  
                ],
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
              "idx": 45,
              "createdUserIdx": 16,
              "createdDateTime": "2021-10-28 16:34:45",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-16 15:51:55",
              "categoryIdx": 6,
              "title": "카카오와 사회혁신가가 만나면:  카카오임팩트 펠로우십 1년의 스토리",
              "content": "혁신가들의 레이블, 카카오임팩트 펠로우십 론칭 후 1년 간의 다양한 시도와 함께 꿈꾸는 변화에 관한 이야기를 소개합니다. \n\n지난 1년 동안 카카오임팩트는 24명의 혁신가를 펠로우로 선정하고, 카카오와 함께 다양한 지원을 해왔습니다. \n그중 혁신가의 목소리와 어젠다를 영향력 있는 콘텐츠와 채널을 통해 널리 알리도록 돕는 ‘확성기’ 지원의 다양한 사례들을 소개하고, 나아가 카카오의 콘텐츠, 기술, 플랫폼과 연계해 사회 문제 해결의 실마리를 찾아가는 이야기를 들려드립니다. \n\n카카오와 사회혁신가가 만나 앞으로 만들게 될 더 나은 세상으로의 변화를 함께 상상해보시기 바랍니다.",
              "contentTag": "#카카오임팩트 #카카오임팩트펠로우십 #펠로우십 #더나은세상 #확성기 #기업재단",
              "spotlightYn": "Y",
              "field": "서비스",
              "sessionType": "A Type",
              "commentYn": "Y",
              "company": "카카오",
              "reservationDate": "20211116",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 4673,
                    "contentsIdx": 45,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423955927",
                    "description": "18:16",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 4671,
                    "contentsIdx": 45,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 112080,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408142730",
                    "description": "If kakao_impact_1022.png",
                    "mainYn": "N"
                  },
                  {
                    "idx": 4672,
                    "contentsIdx": 45,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 117737,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408147897",
                    "description": "If kakao_impact2_1022.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  {
                    "idx": 4666,
                    "contentsIdx": 45,
                    "type": "PC_MAIN_IMAGE",
                    "fileSize": 99463,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408067686",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ],
                "MO_MAIN_IMAGE": [
                  {
                    "idx": 4667,
                    "contentsIdx": 45,
                    "type": "MO_MAIN_IMAGE",
                    "fileSize": 48171,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408079499",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ],
                "PC_IMAGE": [
                  {
                    "idx": 4668,
                    "contentsIdx": 45,
                    "type": "PC_IMAGE",
                    "fileSize": 26449,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408051998",
                    "description": "임팩트.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 4669,
                    "contentsIdx": 45,
                    "type": "MO_IMAGE",
                    "fileSize": 8748,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408092161",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 4670,
                    "contentsIdx": 45,
                    "type": "SHARE_IMAGE",
                    "fileSize": 26449,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408057068",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  {
                    "idx": 4664,
                    "contentsIdx": 45,
                    "type": "PC_SPOTLIGHT",
                    "fileSize": 19235,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420005992",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ],
                "MO_SPOTLIGHT": [
                  {
                    "idx": 4665,
                    "contentsIdx": 45,
                    "type": "MO_SPOTLIGHT",
                    "fileSize": 12715,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420019849",
                    "description": "임팩트.png",
                    "mainYn": "N"
                  }
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  "플랫폼",
                  "소셜임팩트"
                ],
                "TECH_CLASSIFICATION": [
                  
                ],
                "MAIN_EXPOSURE_DAY": [
                  "1Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1467,
                  "contentsIdx": 45,
                  "nameKo": "육심나",
                  "nameEn": "sienna",
                  "company": "카카오임팩트",
                  "occupation": "사무국장",
                  "channelLink": "https://pf.kakao.com/_MiBCd"
                },
                {
                  "idx": 1468,
                  "contentsIdx": 45,
                  "nameKo": "정연주",
                  "nameEn": "juni",
                  "company": "카카오임팩트",
                  "occupation": "사업팀장"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오",
              "speakerName": "sienna육심나",
              "speakerLoginYn": "N",
              "reservationUTC": 1636992000000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 46,
              "createdUserIdx": 16,
              "createdDateTime": "2021-10-28 16:35:04",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-09 16:21:21",
              "categoryIdx": 6,
              "title": "기술과 혁신으로 금융과 고객의 만남을 더 이롭게",
              "content": "카카오뱅크가 혁신하는 이유는 '고객'입니다. \n\n카카오뱅크는 기술과 혁신으로 고객의 금융 생활을 더 풍요롭고, 재밌고, 행복하게 만들 수 있다고 생각합니다. \n\n카카오뱅크의 기술과 혁신이 만드는 고객의 '더 나은 금융 Life'에 대한 이야기를 나누고자 합니다.",
              "contentTag": "#카카오뱅크 #금융혁신 #금융기술 #금융생활 #고객중심 #더나은금융Life",
              "spotlightYn": "Y",
              "field": "서비스",
              "sessionType": "A Type",
              "commentYn": "Y",
              "company": "카카오뱅크",
              "reservationDate": "20211116",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 3611,
                    "contentsIdx": 46,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423774816",
                    "description": "25:18",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 3610,
                    "contentsIdx": 46,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 112894,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408208659",
                    "description": "If Kakao_Bank_CEO_1021.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  {
                    "idx": 3605,
                    "contentsIdx": 46,
                    "type": "PC_MAIN_IMAGE",
                    "fileSize": 839396,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408279954",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ],
                "MO_MAIN_IMAGE": [
                  {
                    "idx": 3606,
                    "contentsIdx": 46,
                    "type": "MO_MAIN_IMAGE",
                    "fileSize": 347381,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408288147",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ],
                "PC_IMAGE": [
                  {
                    "idx": 3607,
                    "contentsIdx": 46,
                    "type": "PC_IMAGE",
                    "fileSize": 173311,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408260152",
                    "description": "뱅크.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 3608,
                    "contentsIdx": 46,
                    "type": "MO_IMAGE",
                    "fileSize": 33569,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408268809",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 3609,
                    "contentsIdx": 46,
                    "type": "SHARE_IMAGE",
                    "fileSize": 839396,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408236038",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  {
                    "idx": 3603,
                    "contentsIdx": 46,
                    "type": "PC_SPOTLIGHT",
                    "fileSize": 40232,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635419975187",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ],
                "MO_SPOTLIGHT": [
                  {
                    "idx": 3604,
                    "contentsIdx": 46,
                    "type": "MO_SPOTLIGHT",
                    "fileSize": 25368,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635419965232",
                    "description": "뱅크.png",
                    "mainYn": "N"
                  }
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  "핀테크",
                  "디지털자산"
                ],
                "TECH_CLASSIFICATION": [
                  
                ],
                "MAIN_EXPOSURE_DAY": [
                  "1Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1265,
                  "contentsIdx": 46,
                  "nameKo": "윤호영",
                  "nameEn": "daniel",
                  "company": "카카오뱅크",
                  "occupation": "CEO",
                  "channelLink": "https://pf.kakao.com/_SxbmHu"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오뱅크",
              "speakerName": "daniel윤호영",
              "speakerLoginYn": "N",
              "reservationUTC": 1636992000000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 50,
              "createdUserIdx": 16,
              "createdDateTime": "2021-10-28 16:35:57",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-09 16:22:22",
              "categoryIdx": 6,
              "title": "NFT, 크리에이터와 팬을 연결하다",
              "content": "지난 2년이 넘는 시간 동안 그라운드 엑스는 블록체인으로 보다 나은 세상을 만들기 위해 노력해왔습니다.\n\n이제 그라운드 엑스가 GX2.0을 통해 새로운 미래를 준비하려고 합니다.\n바로 블록체인의 킬러 서비스를 통해 디지털 자산의 글로벌 리더\"가 되는 것입니다.\n\n그라운드 엑스는 이를 위해 디지털 자산 기술의 핵심 중 하나인 NFT와 이를 활용한 크리에이터 경제에 집중하고자 합니다.\n오늘 저희가 들려드릴 이야기는 그라운드 엑스가 만들어 가고 있는 블록체인을 통해 누구나 디지털 크리에이터가 될 수 있는 세상\"에 관한 이야기입니다.",
              "contentTag": "#Klaytn #GroundX #Blockchain #NFT #NFTArt #DigitalArt #Kakao",
              "spotlightYn": "Y",
              "field": "서비스",
              "sessionType": "A Type",
              "commentYn": "Y",
              "company": "그라운드X",
              "reservationDate": "20211116",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 3629,
                    "contentsIdx": 50,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423774382",
                    "description": "40:23",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 3628,
                    "contentsIdx": 50,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 120909,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408603748",
                    "description": "If Kakao_ground_X_1022.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  {
                    "idx": 3623,
                    "contentsIdx": 50,
                    "type": "PC_MAIN_IMAGE",
                    "fileSize": 286682,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408620862",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ],
                "MO_MAIN_IMAGE": [
                  {
                    "idx": 3624,
                    "contentsIdx": 50,
                    "type": "MO_MAIN_IMAGE",
                    "fileSize": 135627,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408632190",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ],
                "PC_IMAGE": [
                  {
                    "idx": 3625,
                    "contentsIdx": 50,
                    "type": "PC_IMAGE",
                    "fileSize": 107726,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408645143",
                    "description": "그라운드.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 3626,
                    "contentsIdx": 50,
                    "type": "MO_IMAGE",
                    "fileSize": 25748,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408651713",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 3627,
                    "contentsIdx": 50,
                    "type": "SHARE_IMAGE",
                    "fileSize": 107726,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408659096",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  {
                    "idx": 3621,
                    "contentsIdx": 50,
                    "type": "PC_SPOTLIGHT",
                    "fileSize": 48830,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420065973",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ],
                "MO_SPOTLIGHT": [
                  {
                    "idx": 3622,
                    "contentsIdx": 50,
                    "type": "MO_SPOTLIGHT",
                    "fileSize": 29234,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420075935",
                    "description": "그라운드.png",
                    "mainYn": "N"
                  }
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  "크리에이터",
                  "디지털자산"
                ],
                "TECH_CLASSIFICATION": [
                  "블록체인"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "1Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1267,
                  "contentsIdx": 50,
                  "nameKo": "한재선",
                  "nameEn": "jason",
                  "company": "그라운드X",
                  "occupation": "CEO"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "그라운드X",
              "speakerName": "jason한재선",
              "speakerLoginYn": "N",
              "reservationUTC": 1636992000000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 51,
              "createdUserIdx": 16,
              "createdDateTime": "2021-10-28 16:36:19",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-09 16:22:46",
              "categoryIdx": 6,
              "title": "카카오엔터프라이즈가 그려가고 있는  Enterprise IT",
              "content": "카카오엔터프라이즈호가 출항을 한 지 2주년을 맞이했습니다. \n카카오엔터프라이즈가 일궈낸 기술과 비즈니스 성과를 소개하고, 우리가 어떤 회사인지, 어떤 차별점을 갖는지, 궁극적으로 지향하는 바를 나누고자 합니다. \n끝으로 함께 성장하고 상생하고자 하는 노력, 그리고 우수한 개발자들과 함께 만들어갈 미래에 대해 이야기할 예정입니다.",
              "contentTag": "#카카오엔터프라이즈 #AI #클라우드 #상생 #동반성장 #Enterprise #IT #B2B #디지털혁신 ",
              "spotlightYn": "Y",
              "field": "서비스",
              "sessionType": "A Type",
              "commentYn": "Y",
              "company": "카카오엔터프라이즈",
              "reservationDate": "20211116",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 3638,
                    "contentsIdx": 51,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423774672",
                    "description": "09:25",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 3637,
                    "contentsIdx": 51,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 93556,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408788415",
                    "description": "If Kakao_Enterprise_CEO_1021.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  {
                    "idx": 3632,
                    "contentsIdx": 51,
                    "type": "PC_MAIN_IMAGE",
                    "fileSize": 286032,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408760333",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ],
                "MO_MAIN_IMAGE": [
                  {
                    "idx": 3633,
                    "contentsIdx": 51,
                    "type": "MO_MAIN_IMAGE",
                    "fileSize": 153205,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408749130",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ],
                "PC_IMAGE": [
                  {
                    "idx": 3634,
                    "contentsIdx": 51,
                    "type": "PC_IMAGE",
                    "fileSize": 116584,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408717285",
                    "description": "엔터프.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 3635,
                    "contentsIdx": 51,
                    "type": "MO_IMAGE",
                    "fileSize": 32358,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408738538",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 3636,
                    "contentsIdx": 51,
                    "type": "SHARE_IMAGE",
                    "fileSize": 116584,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635408721420",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  {
                    "idx": 3630,
                    "contentsIdx": 51,
                    "type": "PC_SPOTLIGHT",
                    "fileSize": 5790,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420150435",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ],
                "MO_SPOTLIGHT": [
                  {
                    "idx": 3631,
                    "contentsIdx": 51,
                    "type": "MO_SPOTLIGHT",
                    "fileSize": 4113,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635420122864",
                    "description": "엔터프.png",
                    "mainYn": "N"
                  }
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  "B2B",
                  "플랫폼"
                ],
                "TECH_CLASSIFICATION": [
                  "머신러닝/AI",
                  "클라우드"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "1Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1268,
                  "contentsIdx": 51,
                  "nameKo": "백상엽",
                  "nameEn": "andrew",
                  "company": "카카오엔터프라이즈",
                  "occupation": "CEO",
                  "channelLink": "https://pf.kakao.com/_lVmtT"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오엔터프라이즈",
              "speakerName": "andrew백상엽",
              "speakerLoginYn": "N",
              "reservationUTC": 1636992000000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 102,
              "createdUserIdx": 17,
              "createdDateTime": "2021-10-28 17:32:33",
              "lastModifiedUserIdx": 18,
              "lastModifiedDateTime": "2021-11-10 15:35:55",
              "categoryIdx": 7,
              "title": "카카오모빌리티의 MLOps 도입기",
              "content": "카카오모빌리티의 MLOps 도입 초기 주요 key points 소개합니다.",
              "contentTag": "#ML #MLOps",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "3",
              "qnaStartTime": "14:00",
              "qnaEndTime": "15:00",
              "commentYn": "Y",
              "company": "카카오모빌리티",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 4083,
                    "contentsIdx": 102,
                    "type": "FILE",
                    "fileSize": 693788,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636526152575",
                    "description": "카카오모빌리티의 MLOps 도입기_도입 초기의 주요 key points 소개.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 4082,
                    "contentsIdx": 102,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596028",
                    "description": "11:55",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 4081,
                    "contentsIdx": 102,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 104545,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418711520",
                    "description": "0907_카카오모빌리티_Zlatan.El.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 4078,
                    "contentsIdx": 102,
                    "type": "PC_IMAGE",
                    "fileSize": 68770,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418726827",
                    "description": "0907_카카오모빌리티_Zlatan.El_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 4079,
                    "contentsIdx": 102,
                    "type": "MO_IMAGE",
                    "fileSize": 33971,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418743047",
                    "description": "0907_카카오모빌리티_Zlatan.El_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 4080,
                    "contentsIdx": 102,
                    "type": "SHARE_IMAGE",
                    "fileSize": 68770,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418752553",
                    "description": "0907_카카오모빌리티_Zlatan.El_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "머신러닝/AI"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "2Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1349,
                  "contentsIdx": 102,
                  "nameKo": "이동우",
                  "nameEn": "zlatan",
                  "company": "카카오모빌리티",
                  "occupation": "인텔리전스연구팀",
                  "loginEmail": "kysersoze.lee@gmail.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오모빌리티",
              "speakerName": "zlatan이동우",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 103,
              "createdUserIdx": 17,
              "createdDateTime": "2021-10-28 17:35:43",
              "lastModifiedUserIdx": 18,
              "lastModifiedDateTime": "2021-11-10 15:31:47",
              "categoryIdx": 7,
              "title": "안드로이드 기반 키오스크 개발 시작하기",
              "content": "카카오 T 주차 무인 정산기를 개발하면서 알게된 키오스크에 필요한 기능 및 구현 방법을 소개합니다.",
              "contentTag": "#Android #Kiosk",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "3",
              "qnaStartTime": "14:00",
              "qnaEndTime": "15:00",
              "commentYn": "Y",
              "company": "카카오모빌리티",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 3982,
                    "contentsIdx": 103,
                    "type": "FILE",
                    "fileSize": 75085197,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636525904183",
                    "description": "안드로이드 기반 키오스크 개발 시작하기_주차 무인 정산기 개발 사례 공유.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 3981,
                    "contentsIdx": 103,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596231",
                    "description": "20:25",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 3980,
                    "contentsIdx": 103,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 112654,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418564452",
                    "description": "0907_카카오모빌리티_Justin.And.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 3977,
                    "contentsIdx": 103,
                    "type": "PC_IMAGE",
                    "fileSize": 209315,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418579587",
                    "description": "0907_카카오모빌리티_Justin.And_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 3978,
                    "contentsIdx": 103,
                    "type": "MO_IMAGE",
                    "fileSize": 56551,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418615078",
                    "description": "0907_카카오모빌리티_Justin.And_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 3979,
                    "contentsIdx": 103,
                    "type": "SHARE_IMAGE",
                    "fileSize": 209315,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418648748",
                    "description": "0907_카카오모빌리티_Justin.And_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "Android",
                  "IoT",
                  "모바일"
                ],
                "MAIN_EXPOSURE_DAY": [
                  
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1328,
                  "contentsIdx": 103,
                  "nameKo": "이재훈",
                  "nameEn": "justin",
                  "company": "카카오모빌리티",
                  "occupation": "카오너클라이언트팀",
                  "loginEmail": "justin.and@kakaomobility.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오모빌리티",
              "speakerName": "justin이재훈",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 104,
              "createdUserIdx": 18,
              "createdDateTime": "2021-10-28 17:36:33",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-16 09:40:24",
              "categoryIdx": 7,
              "title": "Open Water Diver? OpenSource Diver!",
              "content": "주니어 개발자의 관점에서 장애 상황을 파악했던 경험을 공유합니다.\n어디서 장애가 발생하였는지, 왜 장애가 발생하였는지를 알아가는 과정을 ThreadDump, SoftReference, HashCollision 세 가지 키워드로 설명합니다.\n장애 키워드와 관련된 오픈소스를 보면서 궁금한 점들을 파헤쳐 나갑니다.\n\n💬 이 세션은 소셜 오디오 플랫폼 음mm에서 연사자와 직접 만나는 After Talk이 마련되어 있습니다.\n\n🎙 카카오커머스에서는 어떻게 일할까?💬\nᄂ 일시 : 11월 23일(화) 14:00 ~ 15:00 \nᄂ 링크 : https://www.mm.xyz/event/21ad6e23-3e0c-4684-8094-4dd28d84d624\n\n잠깐! 음mm은 모바일 전용 App입니다.\n- Android 사용자 음mm 다운로드  https://play.google.com/store/apps/details?id=com.kakao.mmoa\n- iOS 사용자 음mm 다운로드  https://apps.apple.com/kr/app/id1567177871",
              "contentTag": "#Java #소프트참조 #해시충돌 #백엔드",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "2",
              "qnaStartTime": "15:00",
              "qnaEndTime": "16:00",
              "commentYn": "Y",
              "company": "카카오커머스",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 4611,
                    "contentsIdx": 104,
                    "type": "FILE",
                    "fileSize": 37109303,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636524881626",
                    "description": "Open Water Diver Open Source Diver.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 4610,
                    "contentsIdx": 104,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596261",
                    "description": "19:19",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 4609,
                    "contentsIdx": 104,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 113407,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418005576",
                    "description": "0913_카카오커머스_Cheeze.Ball.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 4606,
                    "contentsIdx": 104,
                    "type": "PC_IMAGE",
                    "fileSize": 38653,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418046098",
                    "description": "0913_카카오커머스_Cheeze.Ball_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 4607,
                    "contentsIdx": 104,
                    "type": "MO_IMAGE",
                    "fileSize": 23286,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418024453",
                    "description": "0913_카카오커머스_Cheeze.Ball_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 4608,
                    "contentsIdx": 104,
                    "type": "SHARE_IMAGE",
                    "fileSize": 38653,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418037444",
                    "description": "0913_카카오커머스_Cheeze.Ball_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "백엔드"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "2Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1458,
                  "contentsIdx": 104,
                  "nameKo": "박지혜",
                  "nameEn": "cheeze",
                  "company": "카카오커머스",
                  "occupation": "주문개발파트",
                  "loginEmail": "youo78@naver.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오커머스",
              "speakerName": "cheeze박지혜",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 105,
              "createdUserIdx": 17,
              "createdDateTime": "2021-10-28 17:37:12",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-17 13:33:05",
              "categoryIdx": 7,
              "title": "Angular의 Dependency Injection 왜 필요한가요?",
              "content": "Angular의 Dependency Injection 시스템에 대해 깊게 살펴봅니다. 그리고 그것들이 실무에서 어떻게 유용하게 사용되고 있는지 소개합니다.\n\n💬 이 세션은 소셜 오디오 플랫폼 음mm에서 연사자와 직접 만나는 After Talk이 마련되어 있습니다.\n\n🎙 카카오커머스에서는 어떻게 일할까?💬\nᄂ 일시 : 11월 23일(화) 14:00 ~ 15:00 \nᄂ 링크 : https://www.mm.xyz/event/21ad6e23-3e0c-4684-8094-4dd28d84d624\n\n잠깐! 음mm은 모바일 전용 App입니다.\n- Android 사용자 음mm 다운로드  https://play.google.com/store/apps/details?id=com.kakao.mmoa\n- iOS 사용자 음mm 다운로드  https://apps.apple.com/kr/app/id1567177871",
              "contentTag": "#angular #frontend #javascript",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "2",
              "qnaStartTime": "15:00",
              "qnaEndTime": "16:00",
              "commentYn": "Y",
              "company": "카카오커머스",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 4708,
                    "contentsIdx": 105,
                    "type": "FILE",
                    "fileSize": 2246593,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636526583683",
                    "description": "Angular Dependency Injection  그게 왜 필요한가요.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 4707,
                    "contentsIdx": 105,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596236",
                    "description": "15:44",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 4706,
                    "contentsIdx": 105,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 93726,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418465807",
                    "description": "0913_카카오커머스_Johnny.Dev.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 4703,
                    "contentsIdx": 105,
                    "type": "PC_IMAGE",
                    "fileSize": 99161,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418476536",
                    "description": "0913_카카오커머스_Johnny.Dev_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 4704,
                    "contentsIdx": 105,
                    "type": "MO_IMAGE",
                    "fileSize": 42484,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418483468",
                    "description": "0913_카카오커머스_Johnny.Dev_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 4705,
                    "contentsIdx": 105,
                    "type": "SHARE_IMAGE",
                    "fileSize": 99161,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418495091",
                    "description": "0913_카카오커머스_Johnny.Dev_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "웹/프론트엔드"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "2Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1475,
                  "contentsIdx": 105,
                  "nameKo": "김민형",
                  "nameEn": "johnny",
                  "company": "카카오커머스",
                  "occupation": "FE개발파트",
                  "loginEmail": "romz1212@gmail.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오커머스",
              "speakerName": "johnny김민형",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 106,
              "createdUserIdx": 17,
              "createdDateTime": "2021-10-28 17:38:55",
              "lastModifiedUserIdx": 16,
              "lastModifiedDateTime": "2021-11-16 09:40:08",
              "categoryIdx": 7,
              "title": "Commerce Query data Rebuild Success 했을까",
              "content": "데이터 문제로 골치 아팠던 상황을 CQRS를 이용해 해결했던 경험을 공유합니다.\n\n💬 이 세션은 소셜 오디오 플랫폼 음mm에서 연사자와 직접 만나는 After Talk이 마련되어 있습니다.\n\n🎙 카카오커머스에서는 어떻게 일할까?💬\nᄂ 일시 : 11월 23일(화) 14:00 ~ 15:00 \nᄂ 링크 : https://www.mm.xyz/event/21ad6e23-3e0c-4684-8094-4dd28d84d624\n\n잠깐! 음mm은 모바일 전용 App입니다.\n- Android 사용자 음mm 다운로드  https://play.google.com/store/apps/details?id=com.kakao.mmoa\n- iOS 사용자 음mm 다운로드  https://apps.apple.com/kr/app/id1567177871",
              "contentTag": "#CQRS #백엔드 #MongoDB",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "2",
              "qnaStartTime": "15:00",
              "qnaEndTime": "16:00",
              "commentYn": "Y",
              "company": "카카오커머스",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 4605,
                    "contentsIdx": 106,
                    "type": "FILE",
                    "fileSize": 5851587,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636526621435",
                    "description": "Commerce Query data Rebuild Success 했을까.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 4604,
                    "contentsIdx": 106,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596545",
                    "description": "15:14",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 4603,
                    "contentsIdx": 106,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 99138,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418344125",
                    "description": "0913_카카오커머스_Noah.L.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 4600,
                    "contentsIdx": 106,
                    "type": "PC_IMAGE",
                    "fileSize": 44657,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418369944",
                    "description": "0913_카카오커머스_Noah.L_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 4601,
                    "contentsIdx": 106,
                    "type": "MO_IMAGE",
                    "fileSize": 25800,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418394165",
                    "description": "0913_카카오커머스_Noah.L_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 4602,
                    "contentsIdx": 106,
                    "type": "SHARE_IMAGE",
                    "fileSize": 44657,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635418405870",
                    "description": "0913_카카오커머스_Noah.L_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "백엔드"
                ],
                "MAIN_EXPOSURE_DAY": [
                  
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1457,
                  "contentsIdx": 106,
                  "nameKo": "이경렬",
                  "nameEn": "noah",
                  "company": "카카오커머스",
                  "occupation": "주문개발파트",
                  "loginEmail": "orth@kakao.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오커머스",
              "speakerName": "noah이경렬",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            },
            {
              "idx": 107,
              "createdUserIdx": 18,
              "createdDateTime": "2021-10-28 17:39:07",
              "lastModifiedUserIdx": 17,
              "lastModifiedDateTime": "2021-11-10 15:06:45",
              "categoryIdx": 7,
              "title": "Webflux로 막힘없는 프로젝트 만들기",
              "content": "Webflux에서 Blocking I/O 사용 시 발생하는 문제에 대한 이해, 그리고 해결을 위한 툴과 노하우를 공유합니다.",
              "contentTag": "#백엔드 #Webflux",
              "spotlightYn": "N",
              "field": "기술",
              "sessionType": "B Type",
              "qnaStartDay": "3",
              "qnaStartTime": "14:00",
              "qnaEndTime": "15:00",
              "commentYn": "Y",
              "company": "카카오뱅크",
              "reservationDate": "20211117",
              "reservationTime": "1000",
              "linkList": {
                "FILE": [
                  {
                    "idx": 3774,
                    "contentsIdx": 107,
                    "type": "FILE",
                    "fileSize": 4272533,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1636524397590",
                    "description": "Webflux로 막힘없는 프로젝트 만들기_Spring Webflux 와 Blocking IO.pdf",
                    "mainYn": "N"
                  }
                ],
                "IMAGE": [
                  
                ],
                "WEB_URL": [
                  
                ],
                "VIDEO": [
                  {
                    "idx": 3773,
                    "contentsIdx": 107,
                    "type": "VIDEO",
                    "fileSize": 0,
                    "url": "https://tv.kakao.com/embed/player/cliplink/423596552",
                    "description": "16:05",
                    "mainYn": "N"
                  }
                ],
                "PC_THUMBNAIL": [
                  
                ],
                "MO_THUMBNAIL": [
                  
                ],
                "TALK_THUMBNAIL": [
                  
                ],
                "SPEAKER_PROFILE": [
                  {
                    "idx": 3771,
                    "contentsIdx": 107,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 110032,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417696285",
                    "description": "0909_카카오뱅크_DY.ho.png",
                    "mainYn": "N"
                  },
                  {
                    "idx": 3772,
                    "contentsIdx": 107,
                    "type": "SPEAKER_PROFILE",
                    "fileSize": 112047,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417959348",
                    "description": "0909_카카오뱅크_Dan.1114.png",
                    "mainYn": "N"
                  }
                ],
                "PC_MAIN_IMAGE": [
                  
                ],
                "MO_MAIN_IMAGE": [
                  
                ],
                "PC_IMAGE": [
                  {
                    "idx": 3767,
                    "contentsIdx": 107,
                    "type": "PC_IMAGE",
                    "fileSize": 247118,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417917687",
                    "description": "0909_카카오뱅크_Dan.1114, DY.Ho_560.png",
                    "mainYn": "Y"
                  },
                  {
                    "idx": 3768,
                    "contentsIdx": 107,
                    "type": "PC_IMAGE",
                    "fileSize": 247118,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417964324",
                    "description": "0909_카카오뱅크_Dan.1114, DY.Ho_560.png",
                    "mainYn": "Y"
                  }
                ],
                "MO_IMAGE": [
                  {
                    "idx": 3769,
                    "contentsIdx": 107,
                    "type": "MO_IMAGE",
                    "fileSize": 61720,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417908359",
                    "description": "0909_카카오뱅크_Dan.1114, DY.Ho_236.png",
                    "mainYn": "N"
                  }
                ],
                "SHARE_IMAGE": [
                  {
                    "idx": 3770,
                    "contentsIdx": 107,
                    "type": "SHARE_IMAGE",
                    "fileSize": 247118,
                    "url": "https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635417926725",
                    "description": "0909_카카오뱅크_Dan.1114, DY.Ho_560.png",
                    "mainYn": "N"
                  }
                ],
                "PC_SPOTLIGHT": [
                  
                ],
                "MO_SPOTLIGHT": [
                  
                ]
              },
              "relationList": {
                "CLASSIFICATION": [
                  
                ],
                "TECH_CLASSIFICATION": [
                  "백엔드"
                ],
                "MAIN_EXPOSURE_DAY": [
                  "2Day"
                ]
              },
              "contentsSpeakerList": [
                {
                  "idx": 1288,
                  "contentsIdx": 107,
                  "nameKo": "양호철",
                  "nameEn": "dy",
                  "company": "카카오뱅크",
                  "occupation": "서비스아키",
                  "loginEmail": "yh9589@gmail.com"
                },
                {
                  "idx": 1289,
                  "contentsIdx": 107,
                  "nameKo": "박종원",
                  "nameEn": "dan",
                  "company": "카카오뱅크",
                  "occupation": "뱅킹아키",
                  "loginEmail": "shrimpda@kakao.com"
                }
              ],
              "favoriteYn": "N",
              "newCountentsYn": "N",
              "updateCountentsYn": "N",
              "companyName": "카카오뱅크",
              "speakerName": "dy양호철",
              "speakerLoginYn": "N",
              "reservationUTC": 1637078400000,
              "reservationYn": "Y",
              "videoYn": "Y"
            }],
            "count":120}
    """.trimIndent()

    private val sessions: SessionResponse =
        Gson().fromJson(json, SessionResponse::class.java)

    override suspend fun getSessionAll(): List<Session> = sessions.data

    override suspend fun getSessionById(id: Int): Session =
        sessions.data.find { it.idx == id } ?: Session(
            -1,
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            "",
            -1,
            "",
            "",
            -1,
            "",
            -1,
            LinkList(emptyList(), emptyList(), emptyList(), emptyList()),
            "",
            RelationList(emptyList(), emptyList(), emptyList()),
            "",
            "",
            -1,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

    override suspend fun getSessionsByField(field: String): List<Session> =
        sessions.data.filter { it.field == field }

    override suspend fun searchSessions(
        day: String,
        fields: MutableList<String>,
        keywords: MutableList<String>,
        companies: MutableList<String>
    ): List<Session> =
        sessions.data.filter { session ->
            // day
            (day.isBlank() || session.relationList.MAIN_EXPOSURE_DAY.isNotEmpty() && session.relationList.MAIN_EXPOSURE_DAY[0] == day)
                    // field
                    && (fields.isEmpty() || fields.contains(session.field))
                    // keywords
                    && (keywords.isEmpty() || keywords.any { session.relationList.CLASSIFICATION.contains(it) })
                    // company
                    && (companies.isEmpty() || companies.contains(session.companyName))
        }

    override suspend fun getSessionsRelated(id: Int, field: String): List<Session> =
        sessions.data.filter { it.field == field && it.idx != id }

    override suspend fun sortByTitleAsc(): List<Session> =
        sessions.data.sortedBy { it.title }

    override suspend fun sortByTitleDesc(): List<Session> =
        sessions.data.sortedByDescending { it.title }

    override suspend fun sortByCompanyAsc(): List<Session> =
        sessions.data.sortedBy { it.companyName }

    override suspend fun sortByCompanyDesc(): List<Session> =
        sessions.data.sortedByDescending { it.companyName }

    override suspend fun sortByCategoryAsc(): List<Session> =
        sessions.data.sortedBy { it.categoryIdx }

    override suspend fun sortByCategoryDesc(): List<Session> =
        sessions.data.sortedByDescending { it.categoryIdx }

}