package com.example.emotionalintelligence.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;

import org.webrtc.*;

public class VoiceCallActivity extends AppCompatActivity {
    private PeerConnectionFactory peerConnectionFactory;
    private PeerConnection peerConnection;
    private AudioTrack localAudioTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_call);

        initializeWebRTC();
        startCall();
    }

    private void initializeWebRTC() {
        PeerConnectionFactory.InitializationOptions initializationOptions =
                PeerConnectionFactory.InitializationOptions.builder(this)
                        .createInitializationOptions();
        PeerConnectionFactory.initialize(initializationOptions);

        peerConnectionFactory = PeerConnectionFactory.builder().createPeerConnectionFactory();

        AudioSource audioSource = peerConnectionFactory.createAudioSource(new MediaConstraints());
        localAudioTrack = peerConnectionFactory.createAudioTrack("101", audioSource);
    }

    private void startCall() {
        PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(new ArrayList<>());
        peerConnection = peerConnectionFactory.createPeerConnection(rtcConfig, new PeerConnectionAdapter() {
            @Override
            public void onIceCandidate(IceCandidate iceCandidate) {
                // Send ICE candidate to remote peer
            }
        });

        peerConnection.addTrack(localAudioTrack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (peerConnection != null) {
            peerConnection.dispose();
        }
        if (peerConnectionFactory != null) {
            peerConnectionFactory.dispose();
        }
    }
}