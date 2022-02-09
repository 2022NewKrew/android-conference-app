package com.survivalcoding.ifkakao.presentation.sessiondetail

import com.survivalcoding.ifkakao.domain.entity.Category
import com.survivalcoding.ifkakao.domain.entity.Session
import com.survivalcoding.ifkakao.presentation.common.*

class SessionDetailBinderListBuilder {

    private val tmpBinderList = mutableListOf<CommonBinder>()
    private var session: Session? = null

    fun build(): List<CommonBinder> = tmpBinderList

    fun setSession(session: Session): SessionDetailBinderListBuilder {
        this.session = session
        return this
    }

    fun addVideo(
        onPlayButtonClick: (String) -> Unit,
        onShareButtonClick: () -> Unit
    ): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        if (session.videos.isNotEmpty()) {
            tmpBinderList.add(
                SessionVideoBinder(
                    session.videos.first(),
                    session.thumbnailUrl,
                    session.title,
                    onPlayButtonClick,
                    onShareButtonClick
                )
            )
        }
        return this
    }

    fun addCategory(onClick: (Category) -> Unit): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.add(CategoryListBinder(session.categories, onClick))
        return this
    }

    fun addTitle(): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.add(SessionTitleBinder(session.title))
        return this
    }

    fun addContent(): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.add(SessionContentBinder(session.content))
        return this
    }

    fun addTags(): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.add(SessionTagsBinder(session.contentTag))
        return this
    }

    fun addSpeakers(): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.addAll(
            session.contentsSpeakers.map {
                SpeakerBinder(it)
            }
        )
        return this
    }

    fun addFiles(
        onDownloadClick: (fileUri: String, description: String) -> Unit
    ): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.addAll(
            session.files.let {
                it.map { SessionFileBinder(it.uri, it.description, onDownloadClick) }
            }
        )

        return this
    }

    fun addLinks(
        onClickTalk: () -> Unit,
        onClickFacebook: () -> Unit,
        onClickTwit: () -> Unit,
        onClickShare: () -> Unit,
        onClickFavorite: (Boolean) -> Unit
    ): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        tmpBinderList.add(
            SessionLinksBinder(
                onClickTalk,
                onClickFacebook,
                onClickTwit,
                onClickShare,
                session.isFavorite,
                onClickFavorite
            )
        )
        return this
    }

    fun addAssociatedSessions(
        associatedSessionList: List<Session>,
        associatedSessionLastIndex: Int,
        onSessionClick: (Session) -> Unit,
        onMoreButtonClick: () -> Unit
    ): SessionDetailBinderListBuilder {
        val session = this.session ?: return this

        if (associatedSessionList.none { it.idx != session.idx }) return this

        tmpBinderList.add(AssociatedSessionTitleBinder())
        tmpBinderList.addAll(
            associatedSessionList.take(associatedSessionLastIndex)
                .filter { it.idx != session.idx }
                .map {
                    SessionListItemBinder(
                        it, onSessionClick
                    )
                }
        )
        if (associatedSessionLastIndex < associatedSessionList.size - 1) {
            addButton("연관세션 더보기") { onMoreButtonClick() }
        }
        return this
    }

    fun addButton(text: String, onClick: () -> Unit): SessionDetailBinderListBuilder {
        tmpBinderList.add(SessionButtonBinder(text, onClick))
        return this
    }

    fun addFooter(): SessionDetailBinderListBuilder {
        tmpBinderList.add(FooterBinder())
        return this
    }
}