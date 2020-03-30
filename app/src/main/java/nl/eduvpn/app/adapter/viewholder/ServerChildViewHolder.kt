/*
 * This file is part of eduVPN.
 *
 * eduVPN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * eduVPN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with eduVPN.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package nl.eduvpn.app.adapter.viewholder

import android.text.TextUtils
import android.view.View
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import nl.eduvpn.app.BuildConfig
import nl.eduvpn.app.R
import nl.eduvpn.app.databinding.ListItemProviderBinding
import nl.eduvpn.app.databinding.ListItemServerBinding
import nl.eduvpn.app.entity.DiscoveredInstance
import nl.eduvpn.app.entity.Instance
import nl.eduvpn.app.utils.FormattingUtils

/**
 * Viewholder for the provider instance list.
 * Created by Daniel Zolnai on 2016-10-07.
 */
class ServerChildViewHolder(private val binding: ListItemServerBinding) : ServerViewHolder(binding) {

    fun bind(discoveredInstance: DiscoveredInstance) {
        val instance = discoveredInstance.instance
        binding.serverName.text = instance.displayName
        binding.serverUrl.text = FormattingUtils.formatInstanceUrl(instance)
        if (BuildConfig.NEW_ORGANIZATION_LIST_ENABLED) {
            binding.serverIcon.isVisible = false
        } else {
            if (!TextUtils.isEmpty(instance.logoUri)) {
                Picasso.get()
                        .load(instance.logoUri)
                        .fit()
                        .into(binding.serverIcon)
            } else {
                binding.serverIcon.setImageResource(R.drawable.external_provider)
            }
        }
        binding.extraInsetLeft.isVisible = true
        binding.groupArrow.isVisible = false
        if (discoveredInstance.isCachedOnly) {
            binding.serverName.alpha = 0.5f
            binding.serverUrl.alpha = 0.5f
        } else {
            binding.serverName.alpha = 1f
            binding.serverUrl.alpha = 1f
        }
    }

}