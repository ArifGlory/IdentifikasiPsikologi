package com.example.fujimiya.clientpakarpsikologi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;


public class TestActivity extends AppCompatActivity {

    String[] listSoal = {"Bagi saya, sebesar apapun resikonya kita harus melakukan apa yang kita yakini benar. ",
            "Menurut saya, faktor kenyamanan dalam berpakaian jauh lebih penting daripada mengikuti trend terkini. "
    ,"Rasanya kesal,  jika saya diminta untuk meniru hasil pekerjaan orang lain,  yang dianggap lebih baik. ",
            "Saya merasa kesal jika tidak mendapatkan kesempatan untuk menyampaikan ide di dalam sebuah forum. "
    ,"Saat menghadapi masalah,  saya akan mendahulukan pertimbangan pribadi dibanding nasehat orang lain. "
    ,"Ketika berdiskusi, saya akan memilih untuk berdebat, guna mempertahankan pendapat yang saya yakini benar. "
    ,"Menurut saya, jika banyak orang yang menganjurkan untuk melakukan sesuatu, maka sebaiknya kita harus segera melakukan nya. "
    ,"Saya sangat mempertimbangkan fashion terkini, sebelum membeli pakaian. "
    ,"Saya merasa cemas jika mengetahui bahwa hasil pekerjaan yang telah dilakukan ternyata berbeda dengan kebanyakan teman lain. "
    ,"Saya merasa tidak nyaman jika berada dalam situasi dimana pendapat yang saya miliki berkebalikan dengan orang kebanyakan. "
    ,"Saat dihadapkan pada sebuah momen penting, saya tidak berani mengambil keputusan sebelum meminta pendapat ke beberapa orang. "
    ,"Seringkali saya tidak tau apa yang terbaik bagi saya, sehingga memutuskan untuk mengikuti saja pendapat orang lain. "
    ,"Saya yakin dapat mempengaruhi pikiran orang-orang di sekitar saya"
    ,"Bagi saya,  semua kegiatan sehari-hari harus terjadwal dengan teratur. "
    ,"Saya merasa sangat kesal jika tidak dapat melakukan kegiatan yang sudah direncanakan. "
    ,"Mudah bagi saya untuk mengajak teman-teman untuk melakukan sesuatu. "
    ,"Saya akan segera mengambil kesempatan yang ada, meskipun tidak ada jaminan kesuksesan. "
    ,"Satiap pagi, saya selalu membayangkan kegiatan apa saja yang akan dilakukan pada hari ini. "
    ,"Saya menyadari jika saya tidak memiliki bakat untuk memimpin. "
    ,"Bagi saya,  adanya jadwal kegiatan sehari-hari, hanya akan menambah beban pikiran. "
    ,"Saya merasa kesulitan untuk mengatur pola hidup sehari-hari tanpa ada bantuan orang lain. "
    ,"Saya merasa sulit untuk menolak ajakan teman. "
    ,"Seringkali saya menunda untuk mengambil kesempatan yang ada, karena menunggu kesempatan lain yang lebih baik. "
    ,"Kegiatan sehari-hari terlalu rumit untuk diatur, maka dari itu saya menjalani saja apa adanya. "
    ,"Menurut saya, kelebihan yang kita miliki pastilah dapat menutupi segala kekurangan yang ada. "
    ,"Menurut saya, dengan banyak mengalami kejadian buruk dimasa lalu, maka kita akan memiliki banyak pelajaran berharga. "
    ,"Saya merasa senang jika diminta untuk menunjukkan bakat yang dimiliki di hadapan banyak orang "
    ,"Saya merasa mampu bersaing dengan orang-orang hebat yang ada disekitar saya. "
    ,"Saya akan dengan mudah menceritakan tentang kehidupan masa lalu, jika ada orang yang bertanya. "
    ,"Saat menjalin hubungan pertemanan, saya memilih untuk menceritakan kekurangan diri yang dimiliki, daripada menutupinya. "
    ,"Menurut saya,  kelebihan diri yang saya miliki tidak sebanding dengan kelebihan yang dimiliki oleh orang lain. "
    ,"Menurutsaya,  masa lalu merupakan penghambat untuk kita bisa maju. "
    ,"Seringkali saya merasa bingung untuk merespon pujian yang diberikan orang lain atas kelebihan diri yang saya miliki. "
    ,"Seringkali saya merasa minder jika harus berkelompok dengan orang-orang yang lebih pintar daripada saya. "
    ,"Saya memilih untuk menghindari tempat yang mengingatkan saya pada kejadian masa lalu. "
    ,"Saya lebih memilih untuk menutupi kelebihan yang saya miliki daripada menunjukannya kepada orang lain. "
    ,"Saya tau dengan pasti, kegiatan ekstra apa saja yang harus diikuti untuk dapat mengembangkan potensi diri. "
    ,"Bagi saya semua kejadian di masa lalu pasti dapat dijadikan pelajaran bagi masa depan. "
    ,"Saya sangat bersemangat jika diminta untuk menceritakan pengalaman pribadi oleh orang lain. "
    ,"Saya senang mengajak orangtua berdiskusi tentang rencana masa depan yang saya miliki. "
    ,"Saya terbiasa untuk meluangkan waktu khusus guna menyusun rencana masa depan. "
    ,"Saya memiliki kebiasaan untuk mengurutkan hal-hal di dalam kehidupan berdasarkan tingkat kepentingannya. "
    ,"Menurut saya,  tidak perlu pilih-pilih kegiatan ekstra,  karena semuanya pasti baik dan berguna. "
    ,"Menurut saya,  kejadian dimasa lalu adalah sebuah rangkaian kebetulan. "
    ,"Rasanya tidak ada hal yang menarik untuk diceritakan dari kehidupan masa lalu saya. "
    ,"Berdiskusi tentang masa depan sering kali membuat saya merasa bingung. "
    ,"Saya akan mulai membuat perencanaan masa depan,  setelah mengetahui dengan pasti jurusan kuliah nantinya. "
    ,"Saya terbiasa mengerjakan sesuatu dari hal yang termudah dan mengesampingkan hal yang sulit. "
    ,"Penting bagi saya untuk berteman dengan orang yang memiliki keyakinan berbeda. "
    ,"Menurut saya,  mengalah adalah salah satu cara yang baik untuk menjaga hubungan pertemanan. "
    ,"Saya merasa kesal jika ada teman yang tidak mau terbuka kepada saya. "
    ,"Merasakan apa yang sedang dirasakan oleh orang lain adalah hal yang mudah bagi saya. "
    ,"Saya terbiasa meluangkan waktu khusus untuk menanyakan kabar teman-teman. "
    ,"Membahas Ya permasalahan yang sedang dialamai bersama teman adalah hal yang biasa saya lakukan. "
    ,"Sulit bagi saya untuk bisa akrab dengan orang yang memiliki padangan berbeda. "
    ,"Bagi saya,  memberi tanpa harus menerima adalah konsep yang harus dijunjung tinggi dalam hubungan pertemanan. "
    ,"Saya sering merasa kesepian meskipun memiliki banyak teman. "
    ,"Seringkali saya merasa risih jika harus terlibat dalam percakapan yang bersifat personal. "
    ,"Jika ada kabar teman yang sakit,  maka saya akan mencari tahu melalui unggahan media sosialnya. "
    ,"Saat berkumpul dengan teman-teman,  saya lebih memilih untuk baik diam saja daripada harus menceritakan tentang permasalahan yang sedang dialami. "
    ,"Saya menyadari hal apa saja yang perlu ditingkatkan di dalam diri saya. "
    ,"Menurut saya,  dalam satu tahun terakhir ini saya telah berhasil mengembangkan beberapa potensi diri. "
    ,"Saya merasa mudah untuk mengaitkan potensi yang dimiliki dengan rencana depan. "
    ,"Saya tidak suka memiliki banyak waktu luang. "
    ,"Saya terbiasa meluangkan waktu untuk berintrospeksi diri sebagai bentuk upaya perbaikan prilaku. "
    ,"Saya akan selalu berusaha menambah teman baru. "
    ,"Saya sadar bahwa saya memiliki beberapa Kebiasaan buruk yang tidak mungkin untuk di ubah. "
    ,"Menurut saya dalam satu tahun terakhir ini,  tidak banyak perubahan yang terjadi di dalam diri saya. "
    ,"Seringkali saya merasa bingung jika ada yang bertanya tentang potensi apa yang saya miliki. "
    ,"Seringkali saya merasa bingung untuk mengisi waktu luang yang dimiliki. "
    ,"Seringkali secara tidak sengaja,  saya mengulangi Kesalahan yang sama dalam menghadapi sebuah permasalahan. "
    ,"Jika ada pilihan,  saya akan menghindari untuk berada pada lingkungan yang baru. "};

    String[] listSoalEnglish = {"For me, at any risk, we must do what we believe is right."
    ,"In my opinion, the comfort factor in dressing is far more important than following the latest trend."
    ,"I feel annoyed, if I am asked to copy the results of other people's work, which is considered better."
    ,"I feel annoyed if I don't get the opportunity to convey ideas in a forum."
    ,"When facing a problem, I will prioritize personal considerations compared to the advice of others."
    ,"When discussing, I would choose to argue, to maintain the opinion that I believe is right."
    ,"In my opinion, if there are many people who advocate doing something, then we should do it immediately."
    ,"I really consider the latest fashion, before buying clothes."
    ,"I feel anxious to know that the results of the work that has been done are different from most other friends."
    ,"I feel uncomfortable if I am in a situation where my opinion is the opposite of that of most people."
    ,"When faced with an important moment, I did not dare to make a decision before asking for opinions from several people."
    ,"Often I don't know what's best for me, so I decide to just follow the opinions of others."
    ,"I'm sure it can affect the minds of the people around me"
    ,"For me, all daily activities must be scheduled regularly."
    ,"I feel very upset if I can't do the planned activities."
    ,"It's easy for me to invite friends to do something."
    ,"I will immediately take the opportunity, even though there is no guarantee of success."
    ,"In the morning, I always imagine what activities will be done today."
    ,"I realize if I don't have the talent to lead."
    ,"For me, the daily activity schedule will only add to the burden of the mind."
    ,"I find it difficult to regulate my daily lifestyle without the help of others."
    ,"I find it difficult to reject friend's invitation."
    ,"I often delay taking the opportunity, waiting for another better opportunity."
    ,"Daily activities are too complicated to regulate, so I live just the way they are."
    ,"In my opinion, the strength we have must be able to cover up any shortcomings."
    ,"In my opinion, with many bad events in the past, we will have many valuable lessons."
    ,"I feel happy if I am asked to show my talent in front of many people"
    ,"I feel able to compete with the great people around me."
    ,"I will easily tell about the past life, if someone asks."
    ,"When I have a friendship, I choose to share my own shortcomings, rather than cover them up."
    ,"In my opinion, the strength of myself that I have is not comparable with the advantages possessed by others."
    ,"According to me, the past is a barrier for us to get ahead."
    ,"Often I feel confused in responding to the praise that others have given me for my strengths."
    ,"I often feel inferior if I have to group with people who are smarter than me."
    ,"I choose to avoid places that remind me of past events."
    ,"I prefer to cover the strengths that I have rather than showing it to others."
    ,"I know for sure, what extra activities must be followed to be able to develop self potential."
    ,"For me all events in the past can certainly be used as lessons for the future."
    ,"I am very excited if asked to share personal experiences with others."
    ,"I like to invite parents to discuss about the future plans that I have."
    ,"I am used to taking special time to plan for the future."
    ,"I have a habit of sorting things in life based on their importance."
    ,"In my opinion, there is no need to be picky about extra activities, because everything must be good and useful."
    ,"In my opinion, past events are a series of coincidences."
    ,"There doesn't seem to be anything interesting to tell from my past life."
    ,"Discussing about the future often makes me feel confused."
    ,"I will start planning for the future, after knowing exactly what the college major will be."
    ,"I'm used to doing things from the easiest things and putting aside difficult things."
    ,"It's important for me to be friends with people who have different beliefs."
    ,"In my opinion, succumbing is one good way to maintain friendships."
    ,"I feel annoyed if there are friends who don't want to be open to me."
    ,"Feeling what others are feeling is easy for me."
    ,"I'm used to taking special time to ask about friends."
    ,"Discussing Yes the problems that are being shared with friends are the things I usually do."
    ,"It's hard for me to get along with people who have different views."
    ,"For me, giving without having to accept is a concept that must be upheld in friendship."
    ,"I often feel lonely even though I have lots of friends."
    ,"I often feel uncomfortable if I have to engage in personal conversations."
    ,"If there are news of friends who are sick, then I will find out through social media uploads."
    ,"When I gather with friends, I prefer to be quiet, rather than having to tell about the problems that are being experienced."
    ,"I realize what things need to be improved in me."
    ,"In my opinion, in the past year I have succeeded in developing some of my potential."
    ,"I find it easy to associate the potential that is owned by the front plan."
    ,"I don't like having a lot of free time."
    ,"I am used to taking the time to introspect myself as an effort to improve behavior."
    ,"I will always try to add new friends."
    ,"I realize that I have some bad habits that are impossible to change."
    ,"In my opinion, in the past year, not many changes have occurred in me."
    ,"I often feel confused if someone asks about what potential I have."
    ,"Often I feel confused to fill my free time."
    ,"Often I accidentally repeat the same mistake in dealing with a problem."
    ,"If there is a choice, I will avoid being in a new environment."};

    String[] listJenis          = {"Kognitif","Afektif","Psikomotor"};
    String[] listJenisEnglish   = {"Cognitive","Affective","Psychomotor"};

    String[] listKategoriEnglish= {"Autonomy","Enviromental Mastery","Self Acceptance ","Purpose In Life"
    ,"Positive Relationships With Others","Personal Growth"};
    String[] listKategoriIndo   = {"Autonomi","Penguasaan Lingkungan","Penerimaan Diri",
    "Tujuan Hidup","Hubungan dengan sesama","Pertumbuhan Personal"};

    String[] listHigh = {"1.Memiliki determinasi diri & kebebasan\n2.Mampu berperilaku dan berpikir tanpa terpengaruh oleh tekanan sosial\n3.Mampu mengontrol perilaku secara mandiri\n4.Mengevaluasi diri berdasarkan standar personal"
    ,"Merasa mampu serta memiliki keterampilan untuk mengelola lingkungan\nMampu mengontrol kerumitan jadwal kegiatan\nMemanfaatkan kesempatan yang ada disekitar dengan efektif.  "
    ,"Memiliki sikap positif terhadap diri\nMengakui dan menerima segala kualitas dari aspek diri, baik dan buruknya\nMerasa positif terhadap kejadian di masa lalu"
    ,"Memiliki tujuan hidup dan perasaan yang terarah\nMerasa bahwa kehidupan saat ini dan masa lalu memiliki makna\nMemiliki sebuah keyakinan yang memberikan makna dalam hidup\nMemiliki sasaran dan tujuan untuk hidup"
    ,"Memiliki kehangatan, kepuasan dan kepercayaan dalam berhubungan dengan orang lain\nPeduli terhadap kesejahteraan orang lain\nMemiliki rasa empati, cinta dan keintiman\nMemahami konsep take and give dalam hubungan antar manusia."
    ,"Memiliki perasaan untuk selalu mengembangkan diri\nMemandang diri sebagai entitas yang selalu tumbuh dan berkembang\nTerbuka terhadap pengalaman baru\nMerasa perlu untuk menyadari potensi yang dimiliki\nMemandang adanya kemajuan dalam diri dari waktu ke waktu\nPerubahan diri sebagai cerminan dari bertambahnya self knowledge dan pemikiran yang semakin efektif."
    };

    String[] listHighEnglish = {"1. Have self determination & freedom. \n 2.Able to behave and think without being affected by social pressure \n3. Able to control behavior independently \n4. Evaluate yourself based on personal standards"
    ,"Feel capable and have the skills to manage the environment \nCan be able to control the complexity of the schedule of activities \nUsing the opportunities that surround them effectively."
    ,"Have a positive attitude towards yourself. Recognize and accept all qualities from the aspect of self, good and bad. \n Feel positive about events in the past"
    ,"Having a purpose in life and a directed feeling. \n Feeling that life today and the past have meaning \nHaving a belief that gives meaning to life \nHas goals and objectives for life"
    ,"Having warmth, satisfaction and trust in dealing with others. Care for the welfare of others. Have a sense of empathy, love and intimacy. Understand the concept of take and give in human relations."
    ,"Having a feeling to always develop themselves. Looking at ourselves as an entity that is always growing and developing. Open to new experiences. Feeling necessary to realize the potential that is owned. Viewing progress in self over time. Self-change as a reflection of increasing self knowledge and more effective thinking. "};

    String[] listLow        ={"1.Terlalu peduli atas harapan dan evaluasi dari orang lain\n2.Menjadikan pendapat orang lain sebagai dasar pengambilan keputusan penting\n3. Mengikuti tekanan sosial dalam berperilaku dan berpikir"
    ,"Kesulitan untuk mengelola urusan sehari-hari\nMerasa tidak mampu untuk merubah atau memperbaiki keadaan di sekitar\nTidak menyadari adanya kesempatan di sekitar\nMerasa tidak mampu untuk mengontrol “dunia’ di luar dirinya."
    ,"Merasa tidak puas dengan diri sendiri\nMerasa kecewa dengan pengalaman di masa lalu\nMerasa bermasalah dengan beberapa kualitas dari aspek diri\nBerharap untuk menjadi orang lain."
    ,"Merasa kehilangan makna dalam hidup\nMemiliki sedikit tujuan dan sararan dalam hidup\nMerasa tidak terarah\nTidak melihat adanya hikmah dari kejadian di masa lalu"
    ,"Hanya memiliki sedikit hubungan yang saling mempercayai\nMerasa sulit untuk menjadi pribadi yang hangat, terbuka dan peduli kepada orang lain\nMerasa terisolasi dan frustasi dalam menjalin hubungan interpersonal\nTidak mau berkompromi untuk menjaga hubungan yang penting dengan orang lain."
    ,"Merasakan stagnansi diri\nMerasa tidak ada kemajuan dan perkembangan di dalam diri dari waktu ke waktu\nMerasa bosan dan tidak tertarik dengan kehidupan sehari – hari\nMerasa tidak mampu untuk sikap dan perilaku baru"};

    String[] listLowEnglish = {"1. Too caring about the expectations and evaluations of others. \n2.Making other people's opinions the basis of important decisions. \n3.Following social pressure in behaving and thinking"
    ,"Difficulty in managing daily affairs. \n Feeling unable to change or improve the situation around \nNot aware of opportunities around \n Feel unable to control the world outside of him."
    ,"Feeling dissatisfied with yourself. Feeling disappointed with past experiences. Feeling troubled by some qualities from the aspect of ourselves. Hoping to be someone else."
    ,"Feeling loss of meaning in life \nHave a little purpose and hope in life \nNot directed \nNot seeing any wisdom from past events"
    ,"Having only a few relationships that trust each other. \nIt feels difficult to be a warm, open and caring person. \n Feel isolated and frustrated in interpersonal relationships \n Don't want to compromise to maintain important relationships with others."
    ,"Feeling self-stagnant. \nFeeling there is no progress and development within ourselves from time to time. Feeling bored and not interested in everyday life. Feeling incapable of new attitudes and behaviors"};
    private ArrayList<String> Kategori = new ArrayList<String>();
    private ArrayList<String> Isi = new ArrayList<String>();
    private ArrayList<String> Kunci = new ArrayList<String>();
    private ArrayList<Double> nilai = new ArrayList<Double>();
    private ArrayList<String> id_kat = new ArrayList<String>();
    private ArrayList<String> nama_kat = new ArrayList<String>();
    private ArrayList<String> id_jen = new ArrayList<String>();
    public int posisi = 0;
    TextView txt_nourut,txt_kategori;
    Button btn_nex,btn_sel;
   // JustifiedTextView txt_isi;
    TextView txt_isi;
    RadioButton rYes,rNo;
    RadioGroup rgroub;

    int jumlah = 0;

    AlertDialog alert;
    String id_get_kat = "";

    Double average = 0.0;
    Double hasil = 0.0;
    private String activeLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txt_nourut = findViewById(R.id.txt_nourut);
        txt_kategori = findViewById(R.id.txt_Kategori);
        txt_isi = findViewById(R.id.txtPernyataan);
        btn_nex = findViewById(R.id.nextBtn);
        btn_sel = findViewById(R.id.selesaiBtn);

        rYes = findViewById(R.id.rdYa);
        rNo = findViewById(R.id.rdTdk);
        rgroub = findViewById(R.id.rGroup);

        btn_sel.setVisibility(View.INVISIBLE);
        btn_nex.setVisibility(View.INVISIBLE);
        posisi = 0;
        rYes.setEnabled(false);
        rNo.setEnabled(false);
        btn_sel.setEnabled(false);

        activeLang = Locale.getDefault().getLanguage();

        SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
        id_get_kat = sp.getString("id_kategori", "");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myFirebaseRef = database.getReference("detail_psikolog");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rYes.setEnabled(true);
                rNo.setEnabled(true);
                btn_nex.setVisibility(View.VISIBLE);
                btn_sel.setEnabled(false);
                if(!Kategori.isEmpty()){
                    Kategori.clear();
                }
                if(!Isi.isEmpty()){
                    Isi.clear();
                }
                if(!Kunci.isEmpty()){
                    Kunci.clear();
                }

                if(!id_kat.isEmpty()){
                    id_kat.clear();
                }

                if(!nama_kat.isEmpty()){
                    nama_kat.clear();
                }
                if(!id_kat.isEmpty()){
                    id_jen.clear();
                }

                jumlah = 0;
                for(final DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("id_kategori").getValue().toString().equals(id_get_kat)) {
                    final String kategoriku = "";
                    String jenisku = "";
                    DatabaseReference mykateg = database.getReference("kategori");
                    mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (final DataSnapshot child2 : dataSnapshot.getChildren()) {
                                if (child2.child("id_kategori").getValue().toString().equals(child.child("id_kategori").getValue().toString())) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());

                                    DatabaseReference myjenis = database.getReference("jenis");
                                    myjenis.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child3 : dataSnapshot.getChildren()) {
                                                if (child3.child("id_jenis").getValue().toString().equals(child.child("id_jenis").getValue().toString())) {
                                                    txt_kategori.setText("" + child2.child("nama_kategori").getValue().toString() + "-" + child3.child("jenis").getValue().toString());
                                                }
//                                                Kategori.add(child2.child("nama_kategori").getValue().toString()+" - " +child3.child("jenis").getValue().toString());
                                                //id_jen.add(child3.child("jenis").getValue().toString());
                                            }
                                            //txt_kategori.setText(""+Kategori.get(posisi));
//                                            txt_kategori.setText(""+nama_kat.get(posisi)+"-"+nama_jenis.get(posisi));
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

//                    Kategori.add("ID : "+child.child("id_jenis").getValue().toString()+"Kategori "+child.child("id_kategori").getValue().toString());
                    String soal = child.child("psikologis").getValue().toString();
                    String translated = "";
                    if (activeLang.equals("en")){

                        for (int c=0;c<listSoal.length;c++){
                            soal = soal.toLowerCase();
                            if (soal.equals(listSoal[c].toString().toLowerCase())){
                                translated = listSoalEnglish[c];
                                Isi.add(translated);
                            }
                        }
                    }else {
                        Isi.add(child.child("psikologis").getValue().toString());
                    }

                    id_kat.add(child.child("id_kategori").getValue().toString());
                    id_jen.add(child.child("id_jenis").getValue().toString());
                    Kunci.add(child.getKey());

                    jumlah++;
                }
                }
//                Toast.makeText(TestActivity.this,""+(jumlah+1+" Jumlah Kat :"+Kategori.size()),Toast.LENGTH_SHORT).show();


                if(Isi.size() > 0) {

                    if (activeLang.equals("en")){
                        txt_nourut.setText("No : "+(posisi+1)+" from "+(jumlah));
                    }else {
                        txt_nourut.setText("No : "+(posisi+1)+" dari "+(jumlah));
                    }

                    txt_isi.setText("" + Isi.get(posisi));
                }
                if(Isi.size() == 0){
                    txt_isi.setText("Tidak ada soal");
                    rgroub.setVisibility(View.INVISIBLE);
                }
                btn_nex.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void btn_next(View view) {
//        Toast.makeText(TestActivity.this,"Klik"+posisi,Toast.LENGTH_SHORT).show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mykateg = database.getReference("kategori");
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                    if(child2.child("id_kategori").getValue().toString().equals(id_kat.get(posisi))) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());

                        DatabaseReference myjenis = database.getReference("jenis");
                        myjenis.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot child3 : dataSnapshot.getChildren()){
                                    if(child3.child("id_jenis").getValue().toString().equals(id_jen.get(posisi))) {
                                        String namaKategori = child2.child("nama_kategori").getValue().toString();
                                        String jenis        = child3.child("jenis").getValue().toString();

                                        if (activeLang.equals("en")){

                                            for (int c=0;c<listJenis.length;c++){
                                                if (jenis.toLowerCase().equals(listJenis[c].toLowerCase())){
                                                    jenis = listJenisEnglish[c];
                                                }
                                            }
                                        }
                                        if (activeLang.equals("in")){
                                            for (int d=0;d<listKategoriEnglish.length;d++){
                                                if (namaKategori.toLowerCase().equals(listKategoriEnglish[d].toLowerCase())){
                                                    namaKategori = listKategoriIndo[d];
                                                }
                                            }
                                        }

                                        txt_kategori.setText(""+namaKategori+"-"+jenis);
                                    }
//                                                Kategori.add(child2.child("nama_kategori").getValue().toString()+" - " +child3.child("jenis").getValue().toString());
                                    //id_jen.add(child3.child("jenis").getValue().toString());
                                }
                                //txt_kategori.setText(""+Kategori.get(posisi));
//                                            txt_kategori.setText(""+nama_kat.get(posisi)+"-"+nama_jenis.get(posisi));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //nilai.add(posisi,hasil);
        String dari = " dari ";
        if (activeLang.equals("en")){
            dari = " from ";
        }

        rYes.setEnabled(true);
        rNo.setEnabled(true);
        btn_nex.setVisibility(View.INVISIBLE);
        rgroub.clearCheck();
        posisi++;
        if((posisi+1) < Isi.size()){
            txt_nourut.setText("No : "+(posisi+1)+" "+dari+" "+(Isi.size()));
//            txt_kategori.setText(""+id_kat.get(posisi)+"-"+id_jen.get(posisi));
            txt_isi.setText(""+Isi.get(posisi));
            btn_nex.setVisibility(View.INVISIBLE);
        }
        if((posisi+1) == Isi.size()){
            txt_nourut.setText("No : "+(posisi+1)+" "+dari+" "+(Isi.size()));
            txt_isi.setText(""+Isi.get(posisi));
            btn_sel.setVisibility(View.VISIBLE);
            btn_nex.setVisibility(View.INVISIBLE);
        }

    }

    public void onRadioButtonClicked(View view) {
        Double hasil = 0.0;
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdYa:
                if (checked)
                    // YA
                    hasil = 0.013;
//                    nilai.set(posisi,hasil);
                    nilai.add(hasil);
                    Toast.makeText(TestActivity.this,"YA"+nilai.size(),Toast.LENGTH_SHORT).show();
//                    rYes.setEnabled(false);
//                    rNo.setEnabled(false);
                    btn_sel.setEnabled(true);
                    if((posisi+1) < Isi.size()) {
                        btn_nex.setVisibility(View.VISIBLE);
                    }
                    if((posisi+1) == Isi.size()){
                        btn_sel.setVisibility(View.VISIBLE);
                        btn_nex.setVisibility(View.INVISIBLE);
                    }
                    break;
            case R.id.rdTdk:
                if (checked)
                    // TIDAK
                    hasil =  0.0 * 0.013;
//                    nilai.set(posisi,hasil);
                    nilai.add(hasil);
                    Toast.makeText(TestActivity.this,"TIDAK",Toast.LENGTH_SHORT).show();
//                    rYes.setEnabled(false);
//                    rNo.setEnabled(false);
                    btn_sel.setEnabled(true);
                    if((posisi+1) < Isi.size()) {
                        btn_nex.setVisibility(View.VISIBLE);
                    }
                    if((posisi+1) == Isi.size()){
                        btn_sel.setVisibility(View.VISIBLE);
                        btn_nex.setVisibility(View.INVISIBLE);
                    }
                    break;
        }
    }

    public void radiobutton(View view) {
    }

    public void btn_selesai(View view) {
        for(int a=0; a < nilai.size(); a++){
            average+=nilai.get(a);

            //Toast.makeText(TestActivity.this,"Nilai : "+nilai.get(a)+", Kategori : "+id_kat.get(a),Toast.LENGTH_SHORT).show();
        }

        hasil = (((average)/nilai.size())/0.013)*100;


            LayoutInflater layoutInflater = LayoutInflater.from(TestActivity.this);
            View promptView = layoutInflater.inflate(R.layout.hasil, null);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TestActivity.this);
            alertDialogBuilder.setView(promptView);

            final TextView txthasil = promptView.findViewById(R.id.hasil);
            final Button btnselesai = promptView.findViewById(R.id.simpan);
            btnselesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            String nilaiTes = "Nilai Tes :";
            String kategoriMemadai = "\nMasuk Kategori Memadai : \n ";
            String kategoriPengembangan = "\nMasuk Kategori Perlu Pengembangan : \n";
            if (activeLang.equals("en")){
                nilaiTes = "Score : ";
                kategoriMemadai = "\nYou belong to  Adequate Category : \n ";
                kategoriPengembangan = "\nYou belong to the category that needs development : \n";
            }
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mykateg = database.getReference("kategori");
        final String finalNilaiTes = nilaiTes;
        final String finalKategoriMemadai = kategoriMemadai;
        final String finalKategoriPengembangan = kategoriPengembangan;
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                        if(child2.child("id_kategori").getValue().toString().equals(id_get_kat)) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());
                            if(hasil >= 50) {
                                String high = child2.child("heigh").getValue().toString();

                                if (activeLang.equals("en")){
                                    for (int c=0;c<listHigh.length;c++){
                                        if (high.toLowerCase().equals(listHigh[c].toLowerCase())){
                                            high = listHighEnglish[c];
                                        }
                                    }
                                }
                                txthasil.setText(finalNilaiTes +new DecimalFormat("##.##").format(hasil)+"%"+ finalKategoriMemadai +high);

                            }
                            if(hasil < 50){
                                String low = child2.child("low").getValue().toString();

                                if (activeLang.equals("en")){
                                    for (int c=0;c<listLow.length;c++){
                                        if (low.toLowerCase().equals(listLow[c].toLowerCase())){
                                            low = listLowEnglish[c];
                                        }
                                    }
                                }
                                txthasil.setText(finalNilaiTes+new DecimalFormat("##.##").format(hasil)+"%"+ finalKategoriPengembangan +low);
                            }

                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            alert = alertDialogBuilder.create();
            alert.show();

        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        String value = sp.getString("id_user", "");

        Random rand = new Random();
        Map mParent = new HashMap();
        mParent.put("id_user", "" + value);
        mParent.put("id_kategori", "" + id_get_kat);
        mParent.put("nilai", "" + String.format("%.2f", hasil));
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        mParent.put("tanggal", "" + thisDate);
        DatabaseReference ref = database.getReference("histori_kuis");
        ref.push().setValue(mParent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                Toast.makeText(TestActivity.this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {
                Toast.makeText(TestActivity.this,"Data gagal disimpan",Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(TestActivity.this,""+(((average/nilai.size())/0.013) * 100),Toast.LENGTH_SHORT).show();
    }







}
