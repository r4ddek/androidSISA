package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;


import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {


    @BindView(R.id.p_cpf)
    @NotEmpty
    @Length(min = 11, messageResId = R.string.error_cpf_invalida)
    EditText promptCpf;

    @BindView(R.id.p_nome)
    @NotEmpty(messageResId = R.string.error_nome_invalida)
    EditText promptName;

    @BindView(R.id.p_email)
    @NotEmpty(emptyTextResId = R.string.error_email_invalida)
    @Email(messageResId = R.string.error_email_invalida)
    EditText promptEmail;

    @BindView(R.id.p_senha)
    @NotEmpty
    @Password(min = 8, messageResId = R.string.error_senha_invalida)
    EditText promptSenha;

    @BindView(R.id.p_rsenha)
    @NotEmpty
    @ConfirmPassword(messageResId = R.string.error_rsenha_invalida)
    EditText promptRSenha;

    @BindView(R.id.spinnerCursoCadastro)
    Spinner sCurso;
    @BindView(R.id.spinnerPeriodoIngresso)
    Spinner sIngresso;
    @BindView(R.id.spinnerDisciplinaFavorita)
    Spinner sFavorita;
    @BindView(R.id.btContinuarCadastro)
    Button btnSubmit;
    @BindView(R.id.btVoltarCadastro)
    Button btnBack;

    String[] areas;

    Aluno aluno;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ButterKnife.bind(this);

        Intent alunoDevolvido = getIntent();
        areas = getResources().getStringArray(R.array.areaCadstro);
        validator = new Validator(this);
        validator.setValidationListener(this);

        if (alunoDevolvido.hasExtra("Aluno")) {
            Aluno aluno = (Aluno) alunoDevolvido.getSerializableExtra("Aluno");
            promptCpf.setText(aluno.getCpf());
            promptName.setText(aluno.getNome());
            promptEmail.setText(aluno.getEmail());
            sCurso.setSelection(aluno.getsCurso());
            sIngresso.setSelection(aluno.getsIngresso());
           // sFavorita.setSelection(aluno.getsFavorita());
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVoltar = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intentVoltar);
            }
        });

        promptCpf.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {

            private boolean backspacingFlag = false;

            private boolean editedFlag = false;

            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                cursorComplement = s.length()-promptCpf.getSelectionStart();

                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                String phone = string.replaceAll("[^\\d]", "");


                if (!editedFlag) {

                    if (phone.length() > 9 && !backspacingFlag){
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "." + phone.substring(3,6) + "." + phone.substring(6,9) + "-" + phone.substring(9);
                        promptCpf.setText(ans);
                        promptCpf.setSelection(promptCpf.getText().length()-cursorComplement);
                    }
                    else if (phone.length() >= 6 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "." + phone.substring(3,6) + "." + phone.substring(6);
                        promptCpf.setText(ans);
                        promptCpf.setSelection(promptCpf.getText().length()-cursorComplement);

                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 3) + "." + phone.substring(3);
                        promptCpf.setText(ans);
                        promptCpf.setSelection(promptCpf.getText().length()-cursorComplement);
                    }
                } else {
                    editedFlag = false;
                }
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        String cpf = promptCpf.getText().toString();
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");
        StringTokenizer tokens = new StringTokenizer(sIngresso.getSelectedItem().toString(), ".");
        String first = tokens.nextToken();// this will contain "Fruit"
        String second = tokens.nextToken();
        int anoIngresso = Integer.parseInt(first);
        int periodoIngresso = Integer.parseInt(second);
        aluno = new Aluno(cpf,
                promptName.getText().toString(), promptEmail.getText().toString(),
                promptSenha.getText().toString(), anoIngresso, periodoIngresso,
                areas[sFavorita.getSelectedItemPosition()]);
        Intent intentCadastro = new Intent(CadastroActivity.this, Cadastro2Activity.class);
        intentCadastro.putExtra("Aluno", aluno);
        // DEBUG
        Log.d("cpf do aluno", aluno.getCpf());
        Log.d("nome do aluno", aluno.getNome());
        Log.d("email do aluno", aluno.getEmail());
        Log.d("senha do aluno", aluno.getPassword());
        String var = Integer.toString(aluno.getsIngresso());
        Log.d("ano do aluno", var);
        String var2 = Integer.toString(aluno.getPeriodoIngresso());
        Log.d("ingresso do aluno", var2);

        Log.d("favorita do aluno",  areas[sFavorita.getSelectedItemPosition()]);
        startActivity(intentCadastro);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

}
