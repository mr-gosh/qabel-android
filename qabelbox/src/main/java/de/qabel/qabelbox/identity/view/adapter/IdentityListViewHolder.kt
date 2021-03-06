package de.qabel.qabelbox.identity.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import de.qabel.core.config.Identity
import de.qabel.core.index.formatPhoneNumberReadable
import de.qabel.core.index.isValidPhoneNumber
import de.qabel.core.ui.initials
import de.qabel.qabelbox.R
import de.qabel.qabelbox.contacts.extensions.color
import de.qabel.qabelbox.contacts.view.widgets.IdentityIconDrawable
import de.qabel.qabelbox.ui.extensions.setOrGone
import kotlinx.android.synthetic.main.item_identities.view.*
import org.jetbrains.anko.dip

class IdentityListViewHolder(view: View,
                             val onClick: (identity: Identity) -> Unit,
                             val onLongClick: (identity: Identity) -> Boolean) : RecyclerView.ViewHolder(view) {

    fun bindTo(identity: Identity) {
        itemView?.apply {
            val iconSize = context.resources.
                    getDimension(R.dimen.material_drawer_item_profile_icon_width).toInt()
            val mail = identity.email ?: ""
            val phone = if (!identity.phone.isNullOrEmpty() && isValidPhoneNumber(identity.phone))
                formatPhoneNumberReadable(identity.phone) else ""

            item_name.text = identity.alias
            item_details_1.setOrGone(mail)
            item_details_2.setOrGone(phone)
            item_icon.background = IdentityIconDrawable(
                    width = iconSize,
                    height = iconSize,
                    text = identity.initials(),
                    color = identity.color(context))
            setOnClickListener { onClick(identity) }
            setOnLongClickListener { onLongClick(identity) }
        }
    }

}
