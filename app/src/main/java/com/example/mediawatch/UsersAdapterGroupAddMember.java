package com.example.mediawatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawatch.ApiResponse.MediaWatch;
import com.example.mediawatch.ApiResponse.ProjectsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapterGroupAddMember extends RecyclerView.Adapter<UsersAdapterGroupAddMember.ViewHolder> {

    List<String> userList;
    String username;
    Context mContext;
    FirebaseDatabase database;
    DatabaseReference reference;

    String fetchedGroupId;
    FirebaseAuth auth;

    String id;
    List<Member> selectedMembers;

    private ProjectsAdapter.OnItemClickListener onItemClickListener;
//    String currentAdmin;

    public UsersAdapterGroupAddMember(List<String> userList, String userName, Context mContext, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        this.userList = userList;
        this.username = userName;
        this.mContext = mContext;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_add_group, parent,false);
//        Bundle bundle = new Bundle();
//        id = bundle.getString("groupId");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        reference.child("Users").child(userList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                final String otherName = snapshot.child("username").getValue().toString();

                Member myMember = new Member();
                myMember.setMemberName(otherName);


//                String imageURL = snapshot.child("image").getValue().toString();

                holder.textViewUsers.setText(otherName);

//                if (imageURL.equals("null"))
//                {
//                    holder.imageViewUsers.setImageResource(R.drawable.account);
//                }
//                else
//                {
//                    holder.imageViewUsers.setImageResource(R.drawable.account);
//                    //Picasso.get().load(imageURL).into(holder.imageViewUsers);
//                }

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Group group = new Group();


//                        reference.child("members").child(group.getGroupId()).child(otherName).setValue(otherName);
//                        membersRef.child(group.getGroupId()).child(adminMember.getMemberId()).setValue(adminMember);

//                        Intent intent = new Intent(mContext,MyChatActivity.class);
//                        intent.putExtra("username",username);
//                        intent.putExtra("otherName",otherName);
//                        mContext.startActivity(intent);
//                        Member member = new Member();
//                        member.setMemberName(otherName);
//                        reference = reference.child("members");
//                        Intent intent = new Intent(mContext, ChatCreateChannel.class);
//                        String groupAdmin = intent.getStringExtra("groupAdmin");
//                        reference.child(currentAdmin).child(member.getMemberId()).setValue(member);



                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewUsers;
        private CircleImageView imageViewUsers;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUsers = itemView.findViewById(R.id.textViewUsersGroup);
            imageViewUsers = itemView.findViewById(R.id.imageViewUsersGroup);
            cardView = itemView.findViewById(R.id.cardViewGroup);
        }


    }

//    private void populateMemberList() {
//        // Assume you have a method to fetch and populate memberList from your data source
//        // Replace this with your actual data retrieval logic
//
//        // For example, let's say you have a method called fetchMembersFromDataSource()
//        // that retrieves members from your data source (e.g., Firebase Realtime Database)
//
//        // Here's a hypothetical example:
//
//        DatabaseReference membersRef = databaseReference.child("Users");
//
//        membersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                memberList.clear();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Member member = snapshot.getValue(Member.class);
//                    memberList.add(member);
//                }
//
//                // Notify the adapter that the data set has changed
//                memberAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
public interface OnItemClickListener {
    void onItemClick(MediaWatch item);
}
}
// TRENDING MARKETS
// Step 1: IDENTIFY THE TREND. Draw lines i.e. HH & HL(Uptrend) or LH & LL(Downtrend). the purpose of this is to identify the Huge impulsive moves and the retracements/pullbacks so that we can proceed to the next step of identifying trend lines. Use 4 hour chart for this
// Rules of identifying the trend
// Rule 1: To determine whether a market is trending or not, you have to use bigger time frames such as the 4H, the daily or the weekly time frame
// Rule 2a: Uptrend - If market breaks above the previous swing high, we are in an uptrend
// Rule 2b: Downtrend - If the market breaks below the previous swing low, we are in a downtrend
// Reversals:
// From Uptrend to downtrend - If market breaks below the previous pullback level, we reverse and are now ina a downtrend
// From Downtrend to Uptrend - If the market breaks above the previous pull back to continue up, we are now in an uptrend
// From statistics, trends are estimated to occur 30% of the time, so while they are in motion, you've got to know how to take advantage of them.
//
// Step 2: HOW TO IDENTIFY THE BEGINNING OF AN IMPULSIVE MOVE
// METHOD A: Support and resistance levels
// In an uptrend the previous swing point acts as a SUPPORT level and in a downtrend the old swing point acts as a RESISTANCE level
// When market makes a retracement move, it respects previous swing point(support level) which represent the beginning of a new impulsive move
// By drawing support in an uptrend market, we can predict when the next impulsive move will take place
// In a downtrend the previous swing point acts as a RESISTANCE level
// NB: When market makes a retracement move, it respects previous swing point(Resistance level) which represent the beginning of a new impulsive move in the downtrend
// METHOD B: Identify and draw trend lines - Find at least 2 minimum swing points and simply connect them with each other. Always use the 4H and 1D time frame to find obvious time frames
// Purpose of a trend line is to anticipate the next impulsive move with the direction of the market
// The Trend can be an uptrend or a downtrend
// HOW TO TRADE TRENDING MARKETS:
// If it is a bullish market, you look for a buying opportunity
// If it is a bearish market, you look for a selling opportunity
// WHEN IS THE RIGHT TIME TO ENTER A TRADE IN A TRENDING MARKET:
// You always buy at the beginning of an impulsive move and take profits at the end of it for an uptrend
// You always sell at the impulsive move not a retracement level for a downtrend
// NB: Market respects the tend line and when price approach it, the market reverse and continue in the same direction
//
// RANGING MARKETS [Double Tops/Bottoms, Triple Tops/Bottoms]
// Are often called sideways markets, because of their neutral nature makes them appear to drift to the right, horizontally
// A ranging market moves in a horizontal form, where buyers and sellers just keep knocking price back and forth between support and resistance level
// NB: When the market is ranging, it creates equilibrium, buyers are equal to sellers, and there no one is in control. This continues until the range structure breaks out, and a trending condition starts to organize
// HOW TO TRADE RANGING MARKETS: 3 ways
// First Way [Support & Resistance Levels] - Double Tops, Double Bottoms, Triple Tops Triple Bottoms
// Wait for the price to approach support and resistance level then you can buy at key support level and sel at key resistance level
// Second Way [Break Out Position Trade]
// Waiting for the break out either from either the support level or the resistance level. When the market is ranging no one knows what is going to happen, we dont know who si going to be in control of the market
// this is why you have to pay attention to the boundaries buy when one of the players takes control of the market we will see a breakout of the support and control of the market
// The break out means that ranging period is over, and the beginning of a new trend will take place. This means that beginning of a new trend is likely to happen
// Third Way [Pullback Level] - Double Tops, Double Bottoms, Triple Tops Triple Bottoms
// The pullback is another chance to join the trend for traders who didn't enter in the break out
// When market breaks out of the resistance level to indicate the end of the ranging period, and the beginning of a new trend after the break out the market comes back to retest the resistance level that becomes support before it goes up
// NB: Pullbacks don't always occur after every break out, when it occurs, it represents a great opportunity with a good risk/reward ratio
//
// CHOPPY MARKETS
// Are markets that have no clear  directions, when you open your chart and find a lot of noise, you cant even decide if the market is ranging or trending
// Moving sideways in a very small tight range, This is a sign of choppy market that you should stay away from
//
// TIME FRAMES AND TOP DOWN ANALYSIS
// As a price action Trader your primary Time Frame is the 1H, 4H, and the Daily Time Frame
// If you try to trade pin bars or engulfing bars on the 5-minute time frame, you will loose your money, because there is a lot of noise in the lower time frames, and the market will generate alot of false signals because of the hard battles between bears and bulls
// There is no one successful trader that focuses on 1 time frame
// Top Down Analysis - Begin from bigger time frame and to get the picture and switch to the smaller time frame to decide whether to buy or sell the market
// Lets sya you want to trade the 4 hour chart, you have to look at the weekly chart first and then the daily chart, If the weekly and the daily chart analysis align with the 4 hour chart, you can take your trading decision
// To trade the 1H chart first you have to look at the daily. This will help you avoid low probability setups and it will allow you to stay focussed on high probability price action signals
// Importance of Top Down Analysis
// 1. Helps us Identify The Most Important Support and resistance levels
// If you can identify them on weekly chart, you will know what is going to happen when the price approaches this levels on the 4H chart
// 2. Market Structure. Helps you identify whether the market is trending, ranging or even choppy and you will try to find a way to follow them on smaller time frames
// 3. Previous candle. The previous candle in the weekly chart is important because it helps us identify what will happen during a week and it provides us with valuable information about the future market move
// 4. The market condition. What the market is doing on the 4H chart. Is it trending up or down
// Trade counter trends using price action [Counter trend means a reversal]
// This is a double top setup
// TRADING STRATEGIES AND TACTICS
// First - From previous chapters - Identify market trend(uptrend/downtrend)
// Second - Draw support and resistance lines on key levels
// Third - Signals - The candle stick formed
// The Pin Bar Candle Stick Pattern/Hammer/ Shooting star/ 38.2% candle
// Widely used to determine reversal points in the market
// You will learn how to identify potential pin bars signals and the conditions needed for high probability setups
// A pin bar is characterised by a very long wick that shows rejection and indicates that the market will move in the opposite direction
// Bullish [Hammer]- Long lower wicks
// Bearish [Shooting star]- long upper wicks
// Sometimes the signals fail for no apparent reason
// You should evaluate pin bar setups from multiple angles
// Pin bar should respect the following criteria to be traded;
// Pin bar formed in bigger time frames should be taken into consideration. Lower time frames contain a lot of them and should avoided because they generate a lot of false signals
// Pin bar formed in direction of the trend is very powerful than the ones formed against the trend
// Pin bars with longer tails are more powerful
// The major areas to watch for pin bars are: support and resistance, supply and demand zones and moving averages
// Formation of this candle stick chart pattern in these levels give a clear idea about what happens in the market
// If formation of this candle stick occurs near a resistance leve, it indicates that the bears reject prices, and prevent the bulls from breaking this level so prices continue with the trend i.e. downtrend
// Trading the pin bar candle stick with the trend
//
//
//
