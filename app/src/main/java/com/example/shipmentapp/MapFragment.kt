package com.example.shipmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shipmentapp.viewModel.MainViewModel
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.utils.BitmapUtils


class MapFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var styleUrl: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mapTilerKey = "xCZCxg1vxkZUNE2EEVVB"
        styleUrl = "https://api.maptiler.com/maps/streets-v2/style.json?key=${mapTilerKey}"

        context?.let { Mapbox.getInstance(it) }
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapView = view.findViewById<com.mapbox.mapboxsdk.maps.MapView>(R.id.map)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { map ->
            map.cameraPosition = com.mapbox.mapboxsdk.camera.CameraPosition.Builder()
                .target(LatLng(48.705177, 44.508064))
                .zoom(10.0)
                .build()
            map.setStyle(styleUrl) {
                addSymbolAnnotation(mapView, map, it)
                map.uiSettings.setAttributionMargins(15, 0, 0, 15)
            }
    }
    }

    private fun addImagesToStyle(style: Style) {
        style.addImage(
            "complete",
            BitmapUtils.getBitmapFromDrawable(resources.getDrawable(R.drawable.ic_done))!!,
            true
        )
        style.addImage(
            "delivery",
            BitmapUtils.getBitmapFromDrawable(resources.getDrawable(R.drawable.ic_delivery))!!,
            true
        )
    }

    private fun addSymbolAnnotation(mapView: com.mapbox.mapboxsdk.maps.MapView, mapboxMap: MapboxMap, style: Style) {

        addImagesToStyle(style)
        val symbolManager = SymbolManager(mapView, mapboxMap, style)
        symbolManager.iconAllowOverlap = true
        symbolManager.iconIgnorePlacement = true

        viewModel.deliveryList.observe(viewLifecycleOwner) { deliveryList ->
            viewModel.completedList.observe(viewLifecycleOwner) {completeList ->
                symbolManager.annotations.clear()
                for (i in deliveryList) {
                    val symbolOptions = SymbolOptions()
                        .withLatLng(LatLng(i.lat.toDouble(), i.lon.toDouble()))
                        .withIconImage("delivery")
                        .withIconSize(1.3f)

                    symbolManager.create(symbolOptions)
                }
                for (i in completeList) {
                    val symbolOptions = SymbolOptions()
                        .withLatLng(LatLng(i.lat.toDouble(), i.lon.toDouble()))
                        .withIconImage("complete")
                        .withIconSize(1.3f)

                    symbolManager.create(symbolOptions)
                }
            }

        }

    }

}