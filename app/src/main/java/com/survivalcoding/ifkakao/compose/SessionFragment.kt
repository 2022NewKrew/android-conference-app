package com.survivalcoding.ifkakao.compose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.domain.entity.ContentsSpeakerList
import com.example.domain.entity.Data
import com.example.domain.entity.ShareImage
import com.example.domain.entity.Video
import com.google.accompanist.flowlayout.FlowRow
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.compose.ui.EvanTheme
import com.survivalcoding.ifkakao.compose.widget.BorderedText
import com.survivalcoding.ifkakao.compose.widget.CircleImageLoader
import com.survivalcoding.ifkakao.compose.widget.SharedWithSocialIcon
import com.survivalcoding.ifkakao.compose.widget.SingleSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //video
        VideoPart(session.value.linkList?.shareImage, session.value.linkList?.video)
        // description
        val classification = session.value.relationList?.classification ?: listOf()
        val techClassification = session.value.relationList?.techClassification ?: listOf()
        val mergedClassification = classification + techClassification
        DescriptionPart(
            mergedClassification,
            session.value.field,
            session.value.company,
            session.value.contentTag,
            session.value.content, session.value.title
        )
        // profile
        ProfilePart(
            //todo 발표자가 여러명 경우

            url = session.value.linkList?.speakerProfile?.first()?.url,
            item = session.value.contentsSpeakerList?.first()
        )
        // related session
        SessionsPart(relatedSessions)
        ScrollToTop(scrollState, coroutineScope)
    }
}

//https://tv.kakao.com/channel/3693125/cliplink/423791694
@Composable
fun VideoPart(shareImages: List<ShareImage>?, videos: List<Video>?) {
    // val context = LocalContext.current

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                videos?.first()?.url?.let { url ->
                    loadUrl(url)
                }
            }
        }, update = {
            it.loadUrl(videos?.first()?.url ?: "https://tv.kakao.com/")
        })

/*    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = rememberImagePainter(
                data = shareImages?.get(0)?.url,
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Image(
            painterResource(id = R.drawable.ic_baseline_play_arrow_24),
            modifier = Modifier
                .size(156.dp)
                .clickable {
                    videos?.first()?.url?.let {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
                },
            contentDescription = "play button"
        )
        videos?.first()?.description?.let {
            Text(text = it, color = Color.Black)
        }*/

    /*
    *  val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(binding.detailLink.text.toString()))
            startActivity(intent)
    *

}
*/
/*    val context = LocalContext.current

    val player = ExoPlayer.Builder(context)
        .setMediaSourceFactory(
            DefaultMediaSourceFactory(context).setLiveTargetOffsetMs(5000)
        )
        .build()
    val playerView = PlayerView(context)

    // Per MediaItem settings.
    val mediaItem: MediaItem = MediaItem.Builder()
        .setUri(videos?.get(0)?.url ?: "")
        .setLiveConfiguration(
            LiveConfiguration.Builder()
                .setMaxPlaybackSpeed(1.02f)
                .build()
        )
        .build()

    player.setMediaItem(mediaItem)
    playerView.player = player


    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = true

    }

    AndroidView(factory = { playerView })*/

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

/*
    LazyColumn(modifier = Modifier
        .fillMaxSize()) {
        items(sessions.value) { item ->
            SingleSession(item = item)
        }
    }
*/

  for (item in sessions.value) {
        //todo 겹치는 문제
        key(item.idx) {
            SingleSession(item)
        }
    }

}

@Composable
fun ScrollToTop(scrollState: ScrollState, coroutineScope: CoroutineScope) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "if(kakao)2020 보기")
            Text(text = "kakao corp.")
        }
        Image(
            painterResource(id = com.survivalcoding.ifkakao.R.drawable.ic_baseline_arrow_circle_up_24),
            contentDescription = "description of the image",
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFun() {
    val viewModel: MainViewModel = viewModel()
    TopCompose(viewModel)
}