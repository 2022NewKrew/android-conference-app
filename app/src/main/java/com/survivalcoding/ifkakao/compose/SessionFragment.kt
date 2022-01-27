package com.survivalcoding.ifkakao.compose

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.domain.entity.ContentsSpeakerList
import com.example.domain.entity.Data
import com.google.android.exoplayer2.SimpleExoPlayer
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.compose.ui.EvanTheme

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
// todo viewmodel
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
        // video player
        VideoPart()
        // description
        DescriptionPart(session.value.content, session.value.title)
        // profile
        ProfilePart(
            url = session.value.linkList.speakerProfile.first().url,
            item = session.value.contentsSpeakerList.first()
        )
        // relatedSessions
        SessionsPart(relatedSessions)
    }
}

@Composable
fun VideoPart() {
    val context = LocalContext.current
    val exoPlayer = remember(context) { SimpleExoPlayer.Builder(context).build() }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    Glide.with(context).asBitmap()
        .load("https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403306708")
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmap = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
    if (bitmap != null) {
        Image(bitmap!!.asImageBitmap(), "video image", modifier = Modifier.fillMaxWidth())
    } else {
        Text("Loading Image...")
    }
}


@Composable
fun DescriptionPart(content: String, title: String) {
    Column {
        Text(
            text = title, color = Color.White, fontWeight = FontWeight.Bold,
        )
        Text(text = content, color = Color.White)
    }
}

@Composable
fun ProfilePart(url: String, item: ContentsSpeakerList) {
    Row {
        ItemThumbNail(
            url = url,
            Modifier
                .padding(end = 10.dp)
                .size(64.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        )
        Column {
            Text(text = item.nameKo + " " + item.nameEn, color = Color.White)
            Text(text = item.company, color = Color.LightGray)
            Text(text = item.occupation, color = Color.LightGray)
        }
    }
}


@Composable
fun SessionsPart(sessions: State<List<Data>>) {
    Column {
        Text(
            text = "연관세션",
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )
        for (item in sessions.value) {
            key(item.idx) {
                WidgetRelatedSession(item = item)
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun WidgetRelatedSession(item: Data) {
    Row(modifier = Modifier.padding(bottom = 5.dp)) {
        if (item.linkList.moMainImage.isNotEmpty()) {
            ItemThumbNail(url = item.linkList.moMainImage.first().url)
        } else {
            ItemThumbNail("https://t1.kakaocdn.net/service_if_kakao_prod/file/file-1635403306708")
        }
        Column {
            Row(modifier = Modifier.padding(bottom = 5.dp)) {
                Text(
                    text = item.company,
                    color = Color.Yellow,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Yellow,
                        )
                        .padding(5.dp)

                )
                Text(
                    text = item.field, color = Color.White, modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                        )
                        .padding(5.dp)
                )
            }
            Text(text = item.title, color = Color.White)
        }

    }
}

@Composable
fun ItemThumbNail(url: String, modifier: Modifier = Modifier.size(150.dp)) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    Glide.with(context).asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmap = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
    if (bitmap != null) {
        Image(
            bitmap!!.asImageBitmap(), "video image", modifier = modifier
        )
    } else {
        Text("Loading Image...")
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
            }
""".trimIndent()