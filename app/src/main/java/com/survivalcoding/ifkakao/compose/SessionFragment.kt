package com.survivalcoding.ifkakao.compose

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.entity.ContentsSpeakerList
import com.example.domain.entity.Data
import com.example.domain.entity.Video
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.compose.ui.EvanTheme
import com.survivalcoding.ifkakao.compose.widget.*

class SessionFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                EvanTheme {
                    TopCompose(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
// todo viewModel
fun TopCompose(viewModel: MainViewModel) {
    val session = viewModel.session.collectAsState()
    val relatedSessions = viewModel.relatedSessions.collectAsState()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //video
        VideoPart(session.value.linkList?.video)
        // description
        DescriptionPart(
            session.value.relationList?.classification,
            session.value.field,
            session.value.company,
            session.value.contentTag,
            session.value.content, session.value.title
        )
        // profile
        ProfilePart(
            url = session.value.linkList?.speakerProfile?.first()?.url,
            item = session.value.contentsSpeakerList?.first()
        )
        // related session
        SessionsPart(relatedSessions)
    }
}

@Composable
fun VideoPart(videos: List<Video>?) {
    val context = LocalContext.current

    if (videos.isNullOrEmpty()) {
        RectangleImageLoader(url = null, Modifier.fillMaxWidth())
    } else {
        val exoPlayer = remember(context) {
            SimpleExoPlayer.Builder(context).build().apply {
                val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, context.packageName)
                )

                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(
                        Uri.parse(videos[0].url)
                    )

                this.prepare(source)
            }
        }
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                }
            }
        )
    }

}


@Composable
fun DescriptionPart(
    classification: List<String>?,
    field: String?,
    company: String?,
    contentTag: String?,
    content: String?,
    title: String?
) {
    FlowRow(crossAxisSpacing = 5.dp) {
        // field
        field?.let {
            BorderedText(text = it)
        }
        // company
        company?.let {
            BorderedText(text = it)
        }
        // classification
        classification?.let {
            for (item in it) {
                BorderedText(text = item)
            }
        }
    }
    // title
    Text(
        text = title ?: "NO DESCRIPTION",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    // content
    Text(text = content ?: "NO CONTENT")

    // contentTag
    contentTag?.let {
        Text(text = it, color = Color.Gray)
    }

}

@Composable
fun ProfilePart(url: String?, item: ContentsSpeakerList?) {
    // profile
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        CircleImageLoader(url = url)
        Column {
            if (item != null) {
                Text(text = item.nameKo + " " + item.nameEn)
            } else {
                Text(text = "Kakao Krew")
            }
            Text(text = item?.company ?: "카카오")
            Text(text = item?.occupation ?: "Krew")
        }
    }
    // social share
    SharedWithSocialIcon()
}


@Composable
fun SessionsPart(sessions: State<List<Data>>) {
    Text(
        text = "연관세션",
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
    )
    for (item in sessions.value) {
        //todo 겹치는 문제
        key(item.idx) {
            SingleSession(item)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewFun() {
    val viewModel: MainViewModel = viewModel()
    TopCompose(viewModel)
}


val sample = """
   
   {
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
                    "url": "",
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
            }
""".trimIndent()