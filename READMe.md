# 🌰 Ayo-Ayo Game in Java

A console-based implementation of the traditional **Ayo-Ayo (also known as Mancala)** game using Java. This project simulates a two-player turn-based board game with full game logic, user interaction, score tracking, and victory determination.

## 🕹️ Game Rules

- Each player starts with **6 pits**, each containing **4 seeds**.
- Players take turns picking one of their pits (1–6) to **sow** the seeds **counter-clockwise**, dropping one seed in each pit and their own store.
- Players **skip their opponent’s store** while sowing.
- If the last seed lands in a player’s **own store**, they **get another turn**.
- The game ends when **one player’s pits are all empty**.
- Remaining seeds on the board go to the player’s own store.
- The player with the **most seeds in their store wins**.

---

## 🚀 How to Run the Game

### ✅ Prerequisites

- Java Development Kit (JDK) installed – version 8 or above
- A Java IDE (like IntelliJ IDEA, Eclipse) or command-line terminal

### ▶️ Running the Game

1. **Clone this repository**:
   ```bash
   git clone https://github.com/your-username/ayo-ayo-game.git
   cd ayo-ayo-game
   ```

2. **Compile the code**:
   ```bash
   javac Main.java
   ```

3. **Run the game**:
   ```bash
   java Main
   ```

---

## 🧩 Features

- ✅ **Fully playable Ayo-Ayo game** with two players
- 🧠 **Game logic** handles sowing, extra turns, and store skipping
- 🛡️ **Input validation**: only valid pit choices are allowed
- ⚠️ Prevents selecting empty pits
- ⏹️ Automatically detects when the game is over
- 🧮 Displays **final scores and winner**
- 📃 Clear instructions and game rules at launch

---

## 🛠️ Development Journey

### 🎯 Objective
To recreate the fun and strategy of a traditional African board game using object-oriented programming concepts in Java.

### 👨‍💻 Steps

1. **Board Representation**  
   Modeled the board as a 14-element array:
    - Player 1's pits: `board[0–5]`
    - Player 1's store: `board[6]`
    - Player 2's pits: `board[7–12]`
    - Player 2's store: `board[13]`

2. **User Input and Flow Control**  
   Used Java’s `Scanner` to take player input and ensured input validation.

3. **Game Logic**  
   Developed the core sowing mechanism and conditions for extra turns.

4. **Edge Case Handling**
    - Prevented players from picking empty pits.
    - Ended the game when one side was empty.
    - Automatically transferred remaining seeds to respective stores.

5. **User Experience Enhancements**
    - Added clear board display after each move
    - Colorful emojis for clarity and fun
    - Declared the winner or a tie with scores

6. **Testing**  
   Conducted multiple playthroughs to test all possible game-ending scenarios and rule conditions.

---

## 💡 Learnings

- Deepened understanding of **arrays**, **conditional logic**, and **loops** in Java.
- Improved ability to **translate real-world rules into code logic**.
- Built skills in **user input handling** and error prevention in CLI applications.

---