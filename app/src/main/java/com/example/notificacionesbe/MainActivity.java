package com.example.notificacionesbe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Crear constante para identificar nuestra notificación
    private static final int NOTIFICACION_ID=0;
    private static final int NOTIFICACION_ID2=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.btnNotificacion:
                notificacionSimple();
                break;
            case R.id.btnNotificacionAutoCancelable:
                notificacionMasFuncionalidades();
                break;
        }
    }

    private void notificacionSimple() {
        //1. Crear el objeto notificación mediante Notification.Builder
        Notification.Builder builder=new Notification.Builder(this);

        //2. Personalizar la notificación
        //Personalizar la barra de estado (lo que se ve cuando está cerrado)
        builder.setSmallIcon(android.R.drawable.star_on);
        builder.setTicker("Atención!"); // mensaje de aparición breve
        //Personalizar la bandeja del sistema (notificación desplegada)
        builder.setContentTitle("Aviso.");
        builder.setContentText("Este es el mensaje de nuestra notificación al usuario.");
        //Convertir un recurso drawable a un objeto bitmap (LargeIcon)
        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.neptuno);
        builder.setLargeIcon(largeIcon);

        //3. Preparar la acción para su ejecución posterior(depende del usuario)
        Intent i =new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,0);
        builder.setContentIntent(pendingIntent);

        //4. Lanzar la notifiación (mediante NotificationManager)
        NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notificacion = builder.build();
        NM.notify(NOTIFICACION_ID,notificacion);
    }
    private void notificacionMasFuncionalidades(){
        Notification.Builder builder=new Notification.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        //builder.setTicker("Atención!");
        builder.setContentTitle("Aviso.");
        builder.setContentText("Este es el mensaje de nuestra notificación al usuario.");
        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.neptuno);
        builder.setLargeIcon(largeIcon);
        //Mensaje largo
        builder.setStyle(new Notification.BigTextStyle().bigText("Línea de prueba 1\n" +
                "Línea de prueba 2\n" +
                "Línea de prueba 3\n" +
                "Línea de prueba 4\n" +
                "Línea de prueba 5\n" +
                "Línea de prueba 6\n" +
                "Línea de prueba 7\n" +
                "Línea de prueba 8"));
        //Llamar a Activity2
        Intent i =new Intent(this,Activity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,0);
        builder.setContentIntent(pendingIntent);

        //desaparición de la notifiación de la barra de estado
        builder.setAutoCancel(true);

        //Lanzar la notificación
        NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notificacion = builder.build();
        NM.notify(NOTIFICACION_ID2,notificacion);
    }
}