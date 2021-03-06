package com.nandaiqbalh.tugaspbb.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nandaiqbalh.tugaspbb.R;
import com.nandaiqbalh.tugaspbb.activity.book.CartActivity;
import com.nandaiqbalh.tugaspbb.adapter.BookAdapter;
import com.nandaiqbalh.tugaspbb.adapter.SliderAdapter;
import com.nandaiqbalh.tugaspbb.model.Book;
import com.nandaiqbalh.tugaspbb.rest.ApiConfig;
import com.nandaiqbalh.tugaspbb.utils.book.BookResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    ViewPager vpSlider;
    SliderAdapter sliderAdapter;

    // recycler view
    RecyclerView rvLatestBook, rvDsicountBooks;
    ArrayList<Book> dataHolder;

    ImageButton btnCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // init
        inisialisasi(view);

        mainButton();

        // slider
        vpSlider.setAdapter(sliderAdapter);

        setOfflineLatestBook();
        setOfflineLDiscountBook();

        // latest book
//        getLatestBook();

//        Log.d("Buku", "Buku: " + latestBooksArrayList);

        return view;

    }

    private void inisialisasi(View view) {
        vpSlider = view.findViewById(R.id.vp_slider);

        sliderAdapter = new SliderAdapter(getContext());

        rvLatestBook = (RecyclerView) view.findViewById(R.id.rv_latest_book);
        rvDsicountBooks = (RecyclerView) view.findViewById(R.id.rv_discounts);

        btnCart = (ImageButton) view.findViewById(R.id.ib_cart_dashboard);

    }

    private void mainButton(){

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    // data offline
    // ini untuk menampilkan data offline
    private void setOfflineLatestBook(){
        // produk recycler view

        rvLatestBook.setLayoutManager(new LinearLayoutManager(getContext()));

        // MENAMBAHKAN BUKU SECARA OFFLINE DARI INTERNAL PROJECT
        // menambahkan produk ke holder -> Featured Product
        dataHolder = new ArrayList<>();
        Book produk1 = new Book("Geez & Ann #1", "Rintik Sedu", "RS-01", "200", "146", "Bahasa Indonesia",  "99000" , "2500", R.drawable.buku_geez1);
        dataHolder.add(produk1);
        Book produk2 = new Book("Konspirasi Alam Semesta", "Fiersa Besari", "FB-01", "200", "166", "Bahasa Indonesia",  "75000" , "0", R.drawable.buku_kolase);
        dataHolder.add(produk2);
        Book produk3 = new Book("Bumi Manusia", "Pramudya Ananta Toer", "PAT-01", "200", "244", "Bahasa Indonesia",  "144000" , "0", R.drawable.buku_bumi_manusia);
        dataHolder.add(produk3);
        Book produk4 = new Book("Geez & Ann #2", "Rintik Sedu", "RS-02", "200", "146", "Bahasa Indonesia",  "99000" , "10000", R.drawable.buku_geez2);
        dataHolder.add(produk4);
        Book produk5 = new Book("Geez & Ann #3", "Rintik Sedu", "RS-01", "200", "146", "Bahasa Indonesia",  "99000" , "5000", R.drawable.buku_geez3);
        dataHolder.add(produk5);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvLatestBook.setLayoutManager(linearLayoutManager);
        rvLatestBook.setAdapter(new BookAdapter(requireActivity(), dataHolder));
    }

    private void setOfflineLDiscountBook(){
        // produk recycler view

        rvDsicountBooks.setLayoutManager(new LinearLayoutManager(getContext()));

        // MENAMBAHKAN BUKU SECARA OFFLINE DARI INTERNAL PROJECT
        // menambahkan produk ke holder -> Featured Product
        dataHolder = new ArrayList<>();
        Book diskon1 = new Book("Catatan Juang", "Fiersa Besari", "FB-02", "2", "175", "Bahasa Indonesia",  "65000" , "6700", R.drawable.buku_catatan_juang);
        dataHolder.add(diskon1);
        Book diskon2 = new Book("Disforia Inersia", "Wira Nagara", "WN-01", "5", "166", "Bahasa Indonesia",  "75000" , "8000", R.drawable.buku_disforsia_inersia);
        dataHolder.add(diskon2);
        Book diskon3 = new Book("Distilasi Alkena", "Wira Nagara", "WN-02", "4", "177", "Bahasa Indonesia",  "75000" , "10000", R.drawable.buku_distilasi_alkena);
        dataHolder.add(diskon3);
        Book diskon4 = new Book("Atomic Habbits", "James Clear", "JC-01", "3", "244", "English",  "144000" , "10000", R.drawable.buku_atomic);
        dataHolder.add(diskon4);
        Book diskon5 = new Book("The Kite Runner", "Khaled Hosseini", "KH-01", "1", "400", "English",  "125000" , "8000", R.drawable.buku_thekiterunner);
        dataHolder.add(diskon5);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvDsicountBooks.setLayoutManager(linearLayoutManager);
        rvDsicountBooks.setAdapter(new BookAdapter(requireActivity(), dataHolder));
    }

//     // get book with API
//    private ArrayList<Book> latestBooksArrayList = new ArrayList<>();
//
//    private void getLatestBook() {
//        Call<BookResponse> latestBookResponseCall = ApiConfig.getService().latestBooks();
//        latestBookResponseCall.enqueue(new Callback<BookResponse>() {
//            @Override
//            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
//
//                BookResponse respon = response.body();
//
//                Log.d("Buku", "Buku: " + respon.getBooks());
//
//                if (respon.getSuccess() == 1){
//                    latestBooksArrayList = respon.getBooks();
//
//                    Log.d("Buku", "Buku: " + respon.getBooks());
//
//                    displayBook();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<BookResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void displayBook(){
//
//        // latestBooks
//        LinearLayoutManager latestBookLinearLayourManager = new LinearLayoutManager(getActivity());
//        latestBookLinearLayourManager.setOrientation(RecyclerView.HORIZONTAL);
//        rvLatestBook.setLayoutManager(latestBookLinearLayourManager);
//        rvLatestBook.setAdapter(new BookAdapter(requireActivity(), latestBooksArrayList));
//    }

}