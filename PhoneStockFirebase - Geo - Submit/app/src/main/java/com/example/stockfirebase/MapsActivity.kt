package com.example.stockfirebase

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private var id = 0
    lateinit var db : FirebaseDatabase
    lateinit var ref : DatabaseReference
    lateinit var storeList:MutableList<Store>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // initial values
        db = FirebaseDatabase.getInstance()
        ref = db.getReference("StoresCollection")
        storeList = mutableListOf()
        // permissions check
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(permissions, 0)
        }
        //
        bt_map.setOnClickListener {
            LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnCompleteListener {
                val place = LatLng(it.result.latitude, it.result.longitude)
                val marker = MarkerOptions()
                marker.position(place)
                marker.title(tf1_map.text.toString())
                marker.snippet("${tf2_map.text.toString()}, Address:  ${getAddress(place)}")
                mMap.addMarker(marker)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
                addGeoFence(place, tf1_map.text.toString(), tf3_map.text.toString().toFloat(), getAddress(place), ref)
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){
                    storeList.clear()
                    for (e in snapshot.children){
                        val store = e.getValue(Store::class.java)
                        val displace = LatLng(store!!.lat, store.lon)
                        addGeoFenceDisplay(displace,store.name, store.rad, store.address, store.desc)
                    }
                } else {
                    storeList.clear()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }


    fun getAddress(place : LatLng):String{
        val geocoder = Geocoder(this, Locale.getDefault())
        return geocoder.getFromLocation(place.latitude, place.longitude, 1)[0].getAddressLine(0)

    }

    @SuppressLint("MissingPermission")
    fun addGeoFence(place: LatLng, name : String, rad : Float, address: String, ref: DatabaseReference){
        id++
        val geofence = Geofence.Builder()
            .setRequestId(name)
            .setCircularRegion(place.latitude, place.longitude, rad)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()
        val intentGeo = Intent("com.example.stockfirebase.GEOBROADCAST")
        intentGeo.component = ComponentName("com.example.stockfirebase","com.example.stockfirebase.GeoReceiver")
        intentGeo.putExtra("name", name)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            // in case the geofence was trigger here we will broadcast this intent below
            // Intent(this, GeoReceiver::class.java).putExtra("name",name),
            intentGeo,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val geoRequest = GeofencingRequest.Builder().addGeofence(geofence).setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER).build()

        LocationServices.getGeofencingClient(this).addGeofences(geoRequest, pendingIntent)
            .addOnSuccessListener {
                val sid = ref.push().key
                val store = Store(sid!!,name,name,rad,place.latitude,place.longitude,address)
                addstore(ref,store,storeList)
                Toast.makeText(this, "success on adding $name", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failure on adding $name", Toast.LENGTH_SHORT).show()
            }
    }

    fun addstore(ref: DatabaseReference, store: Store, storeList: MutableList<Store>) {
        ref.child(store.id).setValue(store).addOnCompleteListener {
            Toast.makeText(this,"Store Added", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    fun addGeoFenceDisplay(place: LatLng, name : String, rad : Float, address: String, desc: String){
        id++
        val marker = MarkerOptions()
        marker.position(place)
        marker.title(name)
        marker.snippet("$desc, Address:  $address")
        mMap.addMarker(marker)
        val geofence = Geofence.Builder()
            .setRequestId(name)
            .setCircularRegion(place.latitude, place.longitude, rad)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()
        val intentGeo = Intent("com.example.stockfirebase.GEOBROADCAST")
        intentGeo.component = ComponentName("com.example.stockfirebase","com.example.stockfirebase.GeoReceiver")
        intentGeo.putExtra("name", name)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            // in case the geofence was trigger here we will broadcast this intent below
            // Intent(this, GeoReceiver::class.java).putExtra("name",name),
            intentGeo,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val geoRequest = GeofencingRequest.Builder().addGeofence(geofence).setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER).build()

        LocationServices.getGeofencingClient(this).addGeofences(geoRequest, pendingIntent)
            .addOnSuccessListener {
                Toast.makeText(this, "success loading", Toast.LENGTH_SHORT).show()
            }
    }
}