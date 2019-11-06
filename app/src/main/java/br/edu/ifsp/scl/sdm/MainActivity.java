package br.edu.ifsp.scl.sdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Random geradorRandomico;
    private TextView resultadoTextView;
    private Button jogarDadoButton;
    private ImageView resultadoImageView;
    private Spinner numDadosSpinner;
    private ImageView resultado2ImageView;
    private EditText numFacesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geradorRandomico = new Random(System.currentTimeMillis());
        resultadoTextView = findViewById(R.id.resultadoTextView);
        jogarDadoButton = findViewById(R.id.jogarDadoButton);
        jogarDadoButton.setOnClickListener(this);
        resultadoImageView = findViewById(R.id.resultadoImageView);
        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);
        numFacesEditText = findViewById(R.id.numFacesEditText);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.jogarDadoButton) {
            int numDados =
                    Integer.parseInt(numDadosSpinner.getSelectedItem().toString());
            String resultadoText = "Faces sorteadas: ";
            int numFaces;
            try {
                numFaces = Integer.parseInt(numFacesEditText.getText().toString());
            } catch (NumberFormatException e) {
                numFaces = 6;
            }
            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            } else {
                resultadoImageView.setVisibility(View.VISIBLE);
                if (numDados == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                } else {
                    resultado2ImageView.setVisibility(View.GONE);
                    resultadoText = "Face sorteada: ";
                }
            }
            for (int i = 1; i <= numDados; i++) {
                int resultado = geradorRandomico.nextInt(numFaces) + 1;
                resultadoText += resultado + ", ";
                ImageView iv = (i == 1) ? resultadoImageView : resultado2ImageView;
                setImageResource(iv, resultado);
            }
            resultadoTextView.setText(resultadoText.substring(0,
                    resultadoText.lastIndexOf(',')));
        }
    }

    private void setImageResource(ImageView iv, int face) {

        String nomeRes = "dice_" + face;
        int idRes = getResources().getIdentifier(nomeRes,
                "drawable",
                getPackageName());
        iv.setImageResource(idRes);
    }
}
