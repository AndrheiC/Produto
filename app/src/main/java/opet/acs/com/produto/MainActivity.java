package opet.acs.com.produto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Produto> produtos;
    private EditText editNome;
    private Spinner spinnerCategoria;
    private EditText editQuantidade;
    private EditText editValorUnitario;
    private ListView listProduto;
    private ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtos = new ArrayList<>();
        editNome = findViewById(R.id.editNome);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        editQuantidade = findViewById(R.id.editQuantidade);
        editValorUnitario = findViewById(R.id.editValorUnitario);
        listProduto = findViewById(R.id.listProduto);
        atualizarProdutos();
    }

    private void atualizarProdutos(){
        if(adapter == null) {
            adapter = new ProdutoAdapter(getApplicationContext(), R.layout.filme_item, produtos);
            listProduto.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    public void salvarProduto(View v){
        String nome = editNome.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();
        int quantidade = Integer.parseInt(editQuantidade.getText().toString());
        float valorUnitario = Float.parseFloat(editQuantidade.getText().toString());
        Produto a = new Produto();
        a.setNome(nome);
        a.setCategoria(categoria);
        a.setQuantidade(quantidade);
        a.setValorUnitario(valorUnitario);

        produtos.add(a);
        atualizarProdutos();
    }

    public void ordenaPorNota(View v){
        ordenaPorNota(produtos);
    }
    public void ordenaPorNota(List<Produto> produtos) {
        Collections.sort(produtos, new Comparator<Produto>() {
            @Override
            public int compare(Produto f1, Produto f2) {
                return f1.getNome().compareTo(f2.getNome());
            }
        });
        atualizarProdutos();
    }
}
