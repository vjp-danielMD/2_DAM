
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

enum CellType {
    EMPTY, WALL, CHEST, TRAP
}

enum Direction {
    NORTH, EAST, SOUTH, WEST
}

enum TrapType {
    POISON, SPIKE, TELEPORT, ALARM
}

enum GameState {
    EXPLORING, COMBAT, CHEST_OPEN, TRAP_TRIGGERED, RESTING
}

class Player {

    int x, y;
    Direction facing;
    int hp = 100;
    int maxHp = 100;
    int gold = 0;
    int level = 1;
    int exp = 0;
}

class Enemy {

    String name;
    int hp;
    int maxHp;
    int attack;
    int defense;
    int goldReward;
    int expReward;

    Enemy(String name, int hp, int attack, int defense, int gold, int exp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.goldReward = gold;
        this.expReward = exp;
    }
}

class Chest {

    boolean opened = false;
    boolean trapped = false;
    TrapType trapType;
    int gold;

    Chest(boolean trapped, int gold) {
        this.trapped = trapped;
        this.gold = gold;
        if (trapped) {
            trapType = TrapType.values()[new Random().nextInt(TrapType.values().length)];
        }
    }
}

class Trap {

    TrapType type;
    boolean triggered = false;

    Trap(TrapType type) {
        this.type = type;
    }
}

class Dungeon {

    private final CellType[][] map;
    private final Map<String, Chest> chests = new HashMap<>();
    private final Map<String, Trap> traps = new HashMap<>();

    public Dungeon(CellType[][] map) {
        this.map = map;
        initializeChestsAndTraps();
    }

    private void initializeChestsAndTraps() {
        Random rand = new Random();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == CellType.CHEST) {
                    boolean trapped = rand.nextBoolean();
                    int gold = 10 + rand.nextInt(50);
                    chests.put(x + "," + y, new Chest(trapped, gold));
                } else if (map[y][x] == CellType.TRAP) {
                    TrapType type = TrapType.values()[rand.nextInt(TrapType.values().length)];
                    traps.put(x + "," + y, new Trap(type));
                }
            }
        }
    }

    public CellType getCell(int x, int y) {
        if (y < 0 || y >= map.length || x < 0 || x >= map[0].length) {
            return CellType.WALL;
        }
        return map[y][x];
    }

    public Chest getChest(int x, int y) {
        return chests.get(x + "," + y);
    }

    public Trap getTrap(int x, int y) {
        return traps.get(x + "," + y);
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }
}

public class DungeonDemo extends JPanel {

    private final Dungeon dungeon;
    private final Player player;
    private final Random random = new Random();
    private GameState gameState = GameState.EXPLORING;
    private Enemy currentEnemy;
    private String message = "";
    private int messageTimer = 0;
    private Chest currentChest;
    private boolean restAmbushed = false;

    public DungeonDemo() {
        CellType W = CellType.WALL;
        CellType E = CellType.EMPTY;
        CellType C = CellType.CHEST;
        CellType T = CellType.TRAP;

        CellType[][] map = {
            {W, W, W, W, W, W, W, W, W, W, W, W, W, W, W},
            {W, E, E, E, E, E, W, E, E, E, E, T, E, C, W},
            {W, E, W, W, W, E, W, E, W, W, W, W, W, E, W},
            {W, E, W, C, W, E, E, E, W, E, E, E, W, E, W},
            {W, E, W, E, W, W, W, W, W, E, W, T, W, E, W},
            {W, E, E, T, E, E, E, E, E, E, W, E, E, E, W},
            {W, W, W, W, W, E, W, W, W, W, W, W, W, E, W},
            {W, E, E, E, W, E, W, E, E, E, T, E, W, E, W},
            {W, E, W, E, W, E, W, E, W, W, W, E, W, E, W},
            {W, E, W, T, E, E, W, E, W, C, W, E, E, E, W},
            {W, E, W, W, W, W, W, E, W, E, W, W, W, W, W},
            {W, E, E, E, T, E, E, E, W, E, E, E, E, E, W},
            {W, W, W, W, W, W, W, E, W, W, W, W, W, E, W},
            {W, C, E, E, E, E, E, E, E, E, E, E, E, E, W},
            {W, W, W, W, W, W, W, W, W, W, W, W, W, W, W}
        };

        dungeon = new Dungeon(map);
        player = new Player();
        player.x = 1;
        player.y = 13;
        player.facing = Direction.EAST;

        setPreferredSize(new Dimension(1024, 768));
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameState == GameState.EXPLORING) {
                    handleExploringInput(e);
                } else if (gameState == GameState.COMBAT) {
                    handleCombatInput(e);
                } else if (gameState == GameState.CHEST_OPEN) {
                    handleChestInput(e);
                } else if (gameState == GameState.TRAP_TRIGGERED) {
                    handleTrapInput(e);
                } else if (gameState == GameState.RESTING) {
                    handleRestInput(e);
                }
                repaint();
            }
        });

        javax.swing.Timer timer = new javax.swing.Timer(100, e -> {
            if (messageTimer > 0) {
                messageTimer--;
                repaint();
            }
        });
        timer.start();
    }

    private void handleExploringInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W ->
                moveForward();
            case KeyEvent.VK_DOWN, KeyEvent.VK_S ->
                moveBackward();
            case KeyEvent.VK_LEFT, KeyEvent.VK_A ->
                turnLeft();
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D ->
                turnRight();
            case KeyEvent.VK_SPACE ->
                interact();
            case KeyEvent.VK_E ->
                openChestAtPosition();
            case KeyEvent.VK_R ->
                rest();
        }
    }

    private void handleCombatInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1, KeyEvent.VK_A ->
                attackEnemy();
            case KeyEvent.VK_2, KeyEvent.VK_D ->
                defendTurn();
            case KeyEvent.VK_3, KeyEvent.VK_R ->
                runAway();
        }
    }

    private void handleChestInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameState = GameState.EXPLORING;
            currentChest = null;
        }
    }

    private void handleTrapInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameState = GameState.EXPLORING;
        }
    }

    private void handleRestInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (restAmbushed) {
                restAmbushed = false;
                gameState = GameState.COMBAT;
            } else {
                gameState = GameState.EXPLORING;
            }
        }
    }

    private void moveForward() {
        int nx = player.x, ny = player.y;
        switch (player.facing) {
            case NORTH ->
                ny--;
            case SOUTH ->
                ny++;
            case EAST ->
                nx++;
            case WEST ->
                nx--;
        }
        if (dungeon.getCell(nx, ny) != CellType.WALL) {
            player.x = nx;
            player.y = ny;
            checkForEncounter();
            checkForTrap();
        }
    }

    private void moveBackward() {
        int nx = player.x, ny = player.y;
        switch (player.facing) {
            case NORTH ->
                ny++;
            case SOUTH ->
                ny--;
            case EAST ->
                nx--;
            case WEST ->
                nx++;
        }
        if (dungeon.getCell(nx, ny) != CellType.WALL) {
            player.x = nx;
            player.y = ny;
            checkForEncounter();
            checkForTrap();
        }
    }

    private void checkForEncounter() {
        if (random.nextInt(100) < 15) { // 15% chance
            spawnEnemy();
        }
    }

    private void checkForTrap() {
        Trap trap = dungeon.getTrap(player.x, player.y);
        if (trap != null && !trap.triggered) {
            trap.triggered = true;
            triggerTrap(trap);
        }
    }

    private void triggerTrap(Trap trap) {
        gameState = GameState.TRAP_TRIGGERED;
        switch (trap.type) {
            case POISON -> {
                int damage = 10 + random.nextInt(15);
                player.hp -= damage;
                message = "POISON TRAP! You take " + damage + " damage!";
            }
            case SPIKE -> {
                int damage = 15 + random.nextInt(20);
                player.hp -= damage;
                message = "SPIKE TRAP! You take " + damage + " damage!";
            }
            case TELEPORT -> {
                // Teleport to random location
                do {
                    player.x = 1 + random.nextInt(13);
                    player.y = 1 + random.nextInt(13);
                } while (dungeon.getCell(player.x, player.y) == CellType.WALL);
                message = "TELEPORT TRAP! You've been moved!";
            }
            case ALARM -> {
                message = "ALARM TRAP! Monsters alerted!";
                spawnEnemy();
            }
        }
        if (player.hp < 0) {
            player.hp = 0;
        }
    }

    private void spawnEnemy() {
        String[] names = {"Goblin", "Orc", "Skeleton", "Zombie", "Wraith", "Spider"};
        String name = names[random.nextInt(names.length)];
        int hp = 20 + random.nextInt(30) + (player.level * 5);
        int attack = 5 + random.nextInt(10) + player.level;
        int defense = 2 + random.nextInt(5);
        int gold = 5 + random.nextInt(20);
        int exp = 10 + random.nextInt(15);

        currentEnemy = new Enemy(name, hp, attack, defense, gold, exp);
        gameState = GameState.COMBAT;
        message = "A " + name + " appears!";
        messageTimer = 20;
    }

    private void interact() {
        // Check forward cell
        int nx = player.x, ny = player.y;
        switch (player.facing) {
            case NORTH ->
                ny--;
            case SOUTH ->
                ny++;
            case EAST ->
                nx++;
            case WEST ->
                nx--;
        }

        CellType cell = dungeon.getCell(nx, ny);
        if (cell == CellType.CHEST) {
            openChest(nx, ny);
        }
    }

    private void openChestAtPosition() {
        // Open chest at current position
        CellType cell = dungeon.getCell(player.x, player.y);
        if (cell == CellType.CHEST) {
            openChest(player.x, player.y);
        } else {
            message = "No chest here.";
            messageTimer = 20;
        }
    }

    private void rest() {
        if (player.hp == player.maxHp) {
            message = "You're already at full health!";
            messageTimer = 20;
            return;
        }

        gameState = GameState.RESTING;

        // 25% chance of ambush
        if (random.nextInt(100) < 25) {
            restAmbushed = true;
            int healAmount = player.maxHp / 4; // Only heal 25% if ambushed
            player.hp = Math.min(player.maxHp, player.hp + healAmount);
            message = "You rested for " + healAmount + " HP but were AMBUSHED!";
            spawnEnemy();
        } else {
            // Full heal if safe
            int healAmount = player.maxHp - player.hp;
            player.hp = player.maxHp;
            message = "You rested safely and recovered " + healAmount + " HP!";
        }
        messageTimer = 30;
    }

    private void openChest(int x, int y) {
        Chest chest = dungeon.getChest(x, y);
        if (chest == null || chest.opened) {
            message = "This chest is empty.";
            messageTimer = 20;
            return;
        }

        currentChest = chest;
        chest.opened = true;
        gameState = GameState.CHEST_OPEN;

        if (chest.trapped) {
            int damage = 10 + random.nextInt(15);
            player.hp -= damage;
            message = "The chest was trapped! -" + damage + " HP";
        } else {
            message = "Chest opened safely!";
        }

        player.gold += chest.gold;
        if (player.hp < 0) {
            player.hp = 0;
        }
    }

    private void attackEnemy() {
        if (currentEnemy == null) {
            return;
        }

        int damage = Math.max(1, (8 + random.nextInt(12) + player.level) - currentEnemy.defense);
        currentEnemy.hp -= damage;
        message = "You hit for " + damage + " damage!";
        messageTimer = 20;

        if (currentEnemy.hp <= 0) {
            defeatEnemy();
        } else {
            enemyAttack();
        }
    }

    private void defendTurn() {
        message = "You brace for impact!";
        messageTimer = 20;
        int damage = Math.max(0, currentEnemy.attack - 5);
        player.hp -= damage;
        message += " Enemy hits for " + damage + " damage!";
        if (player.hp < 0) {
            player.hp = 0;
        }
    }

    private void runAway() {
        if (random.nextInt(100) < 50) {
            message = "You escaped!";
            messageTimer = 20;
            gameState = GameState.EXPLORING;
            currentEnemy = null;
        } else {
            message = "Can't escape!";
            messageTimer = 20;
            enemyAttack();
        }
    }

    private void enemyAttack() {
        int damage = Math.max(1, currentEnemy.attack - 2);
        player.hp -= damage;
        message += "\n" + currentEnemy.name + " attacks for " + damage + " damage!";
        messageTimer = 20;
        if (player.hp < 0) {
            player.hp = 0;
        }

        if (player.hp == 0) {
            message = "You have been defeated!";
            gameState = GameState.EXPLORING;
            player.hp = player.maxHp;
            player.x = 1;
            player.y = 13;
        }
    }

    private void defeatEnemy() {
        player.gold += currentEnemy.goldReward;
        player.exp += currentEnemy.expReward;
        message = currentEnemy.name + " defeated! +" + currentEnemy.goldReward + " gold, +" + currentEnemy.expReward + " exp";
        messageTimer = 30;

        // Check level up
        int expNeeded = player.level * 100;
        if (player.exp >= expNeeded) {
            player.level++;
            player.exp -= expNeeded;
            player.maxHp += 10;
            player.hp = player.maxHp;
            message += "\nLEVEL UP! Now level " + player.level;
        }

        gameState = GameState.EXPLORING;
        currentEnemy = null;
    }

    private void turnLeft() {
        player.facing = switch (player.facing) {
            case NORTH ->
                Direction.WEST;
            case WEST ->
                Direction.SOUTH;
            case SOUTH ->
                Direction.EAST;
            case EAST ->
                Direction.NORTH;
        };
    }

    private void turnRight() {
        player.facing = switch (player.facing) {
            case NORTH ->
                Direction.EAST;
            case EAST ->
                Direction.SOUTH;
            case SOUTH ->
                Direction.WEST;
            case WEST ->
                Direction.NORTH;
        };
    }

    private void drawWall(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, float depth) {
        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};

        float brightness = 0.8f / (depth + 0.5f);
        brightness = Math.min(1.0f, brightness);

        int baseColor = (int) (100 * brightness);
        int r = Math.max(0, baseColor);
        int g = Math.max(0, baseColor - 10);
        int b = Math.max(0, baseColor - 15);
        g2d.setColor(new Color(r, g, b));
        g2d.fillPolygon(xPoints, yPoints, 4);

        g2d.setColor(new Color(30, 25, 20));
        g2d.drawPolygon(xPoints, yPoints, 4);

        int brickRows = Math.max(3, (int) (8 / (depth + 0.5f)));
        for (int i = 0; i < brickRows; i++) {
            float t = (i + 0.5f) / brickRows;
            int bx1 = (int) (x1 + (x4 - x1) * t);
            int by1 = (int) (y1 + (y4 - y1) * t);
            int bx2 = (int) (x2 + (x3 - x2) * t);
            int by2 = (int) (y2 + (y3 - y2) * t);

            g2d.setColor(new Color(20, 18, 15, 100));
            g2d.drawLine(bx1, by1, bx2, by2);
        }
    }

    private void drawChest(Graphics2D g2d, int cx, int cy, int cw, int ch, float depth, boolean opened) {
        float brightness = 0.9f / (depth + 0.5f);
        int woodR = (int) (139 * brightness);
        int woodG = (int) (90 * brightness);
        int woodB = (int) (43 * brightness);

        g2d.setColor(new Color(woodR, woodG, woodB));
        g2d.fillRect(cx, cy, cw, ch);

        if (!opened) {
            g2d.setColor(new Color(woodR - 20, woodG - 20, woodB - 10));
            g2d.fillRect(cx, cy, cw, ch / 3);

            int metalSize = Math.max(2, cw / 8);
            g2d.setColor(new Color(180, 180, 140));
            g2d.fillRect(cx + cw / 2 - metalSize / 2, cy + ch / 3, metalSize, ch / 3);
        } else {
            // Open chest
            g2d.setColor(new Color(woodR - 20, woodG - 20, woodB - 10));
            int[] lidX = {cx, cx + cw, cx + cw, cx};
            int[] lidY = {cy - ch / 3, cy - ch / 3, cy, cy};
            g2d.fillPolygon(lidX, lidY, 4);
        }

        g2d.setColor(new Color(40, 30, 20));
        g2d.drawRect(cx, cy, cw, ch);
    }

    private void drawTrap(Graphics2D g2d, int cx, int cy, int cw, int ch, float depth) {
        float brightness = 0.6f / (depth + 0.5f);
        int r = (int) (150 * brightness);
        int gr = (int) (50 * brightness);

        g2d.setColor(new Color(r, gr, 0));
        g2d.fillRect(cx + cw / 4, cy + ch * 3 / 4, cw / 2, ch / 8);

        g2d.setColor(new Color(100, 30, 0));
        for (int i = 0; i < 3; i++) {
            int x = cx + cw / 4 + (i * cw / 6);
            g2d.drawLine(x, cy + ch * 3 / 4, x, cy + ch);
        }
    }

    private void drawEnemy(Graphics2D g2d, int w, int h) {
        int cx = w / 2;
        int cy = h / 3;
        int size = 120;

        // Body
        g2d.setColor(new Color(60, 80, 40));
        g2d.fillOval(cx - size / 2, cy, size, size * 3 / 2);

        // Head
        g2d.setColor(new Color(80, 100, 60));
        g2d.fillOval(cx - size / 3, cy - size / 2, size * 2 / 3, size * 2 / 3);

        // Eyes (red)
        g2d.setColor(Color.RED);
        g2d.fillOval(cx - size / 6, cy - size / 3, size / 8, size / 8);
        g2d.fillOval(cx + size / 12, cy - size / 3, size / 8, size / 8);

        // Arms
        g2d.setColor(new Color(60, 80, 40));
        g2d.fillOval(cx - size * 3 / 4, cy + size / 2, size / 3, size);
        g2d.fillOval(cx + size * 5 / 12, cy + size / 2, size / 3, size);

        // Weapon
        g2d.setColor(new Color(120, 120, 120));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(cx + size / 2, cy + size / 2, cx + size * 3 / 4, cy - size / 4);
        g2d.setColor(new Color(150, 150, 150));
        g2d.fillOval(cx + size * 3 / 4 - 8, cy - size / 4 - 8, 16, 16);
    }

    private void drawMinimap(Graphics2D g2d) {
        int x = 20, y = 20, size = 150;
        g2d.setColor(new Color(0, 0, 0, 220));
        g2d.fillRect(x, y, size, size);
        g2d.setColor(new Color(100, 100, 100));
        g2d.drawRect(x, y, size, size);

        int grid = size / 15;
        for (int row = 0; row < dungeon.getHeight(); row++) {
            for (int col = 0; col < dungeon.getWidth(); col++) {
                CellType cell = dungeon.getCell(col, row);
                if (cell == CellType.WALL) {
                    g2d.setColor(new Color(50, 50, 50));
                    g2d.fillRect(x + col * grid, y + row * grid, grid, grid);
                } else if (cell == CellType.CHEST) {
                    Chest chest = dungeon.getChest(col, row);
                    if (chest != null && !chest.opened) {
                        g2d.setColor(new Color(180, 120, 0));
                        g2d.fillRect(x + col * grid + 2, y + row * grid + 2, grid - 4, grid - 4);
                    }
                } else if (cell == CellType.TRAP) {
                    Trap trap = dungeon.getTrap(col, row);
                    if (trap != null && !trap.triggered) {
                        g2d.setColor(new Color(200, 50, 50));
                        g2d.fillRect(x + col * grid + 2, y + row * grid + 2, grid - 4, grid - 4);
                    }
                }
            }
        }
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + player.x * grid + 2, y + player.y * grid + 2, grid - 4, grid - 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();
        int viewH = (h * 75) / 100;
        int horizon = viewH / 2;

        if (gameState == GameState.COMBAT) {
            drawCombatScreen(g2d, w, h);
        } else {
            drawExplorationView(g2d, w, h, viewH, horizon);
        }

        drawUI(g2d, w, h, viewH);
    }

    private void drawExplorationView(Graphics2D g2d, int w, int h, int viewH, int horizon) {
        GradientPaint ceiling = new GradientPaint(0, 0, new Color(5, 5, 10), 0, horizon, new Color(15, 12, 10));
        g2d.setPaint(ceiling);
        g2d.fillRect(0, 0, w, horizon);

        GradientPaint floor = new GradientPaint(0, horizon, new Color(20, 18, 15), 0, viewH, new Color(10, 8, 5));
        g2d.setPaint(floor);
        g2d.fillRect(0, horizon, w, viewH - horizon);

        int dx = 0, dy = 0, leftX = 0, leftY = 0, rightX = 0, rightY = 0;
        switch (player.facing) {
            case NORTH -> {
                dy = -1;
                leftX = -1;
                rightX = 1;
            }
            case SOUTH -> {
                dy = 1;
                leftX = 1;
                rightX = -1;
            }
            case EAST -> {
                dx = 1;
                leftY = -1;
                rightY = 1;
            }
            case WEST -> {
                dx = -1;
                leftY = 1;
                rightY = -1;
            }
        }

        for (int depth = 5; depth >= 0; depth--) {
            float depthRatio = (float) depth / 5.0f;
            float scale = 1.0f - (depthRatio * 0.85f);
            int cellW = (int) (w * scale * 0.3f);
            int cellH = (int) (viewH * scale * 0.6f);
            int cellY = horizon - cellH / 2;

            int px = player.x + (dx * depth);
            int py = player.y + (dy * depth);
            CellType center = dungeon.getCell(px, py);

            int lx = px + leftX;
            int ly = py + leftY;
            CellType left = dungeon.getCell(lx, ly);

            int rx = px + rightX;
            int ry = py + rightY;
            CellType right = dungeon.getCell(rx, ry);

            if (left == CellType.WALL) {
                int x1 = (w / 2) - cellW * 2;
                int x2 = (w / 2) - cellW;
                drawWall(g2d, x1, cellY, x2, cellY, x2, cellY + cellH, x1, cellY + cellH, depth);
            }

            if (right == CellType.WALL) {
                int x1 = (w / 2) + cellW;
                int x2 = (w / 2) + cellW * 2;
                drawWall(g2d, x1, cellY, x2, cellY, x2, cellY + cellH, x1, cellY + cellH, depth);
            }

            if (center == CellType.WALL) {
                int x1 = (w / 2) - cellW;
                int x2 = (w / 2) + cellW;
                drawWall(g2d, x1, cellY, x2, cellY, x2, cellY + cellH, x1, cellY + cellH, depth);
            } else if (center == CellType.CHEST) {
                Chest chest = dungeon.getChest(px, py);
                if (chest != null) {
                    int chestW = cellW;
                    int chestH = cellH / 2;
                    int chestX = (w / 2) - chestW / 2;
                    int chestY = cellY + cellH - chestH;
                    drawChest(g2d, chestX, chestY, chestW, chestH, depth, chest.opened);
                }
            } else if (center == CellType.TRAP) {
                Trap trap = dungeon.getTrap(px, py);
                if (trap != null && !trap.triggered) {
                    int trapW = cellW;
                    int trapH = cellH / 3;
                    int trapX = (w / 2) - trapW / 2;
                    int trapY = cellY + cellH - trapH;
                    drawTrap(g2d, trapX, trapY, trapW, trapH, depth);
                }
            }
        }
    }

    private void drawCombatScreen(Graphics2D g2d, int w, int h) {
        // Dark background
        g2d.setColor(new Color(10, 5, 5));
        g2d.fillRect(0, 0, w, h);

        // Draw enemy
        if (currentEnemy != null) {
            drawEnemy(g2d, w, h);

            // Enemy stats
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
            g2d.drawString(currentEnemy.name, w / 2 - 80, 50);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 18));

            // HP bar
            int barW = 200;
            int barH = 20;
            int barX = w / 2 - barW / 2;
            int barY = 80;
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(barX, barY, barW, barH);
            g2d.setColor(Color.RED);
            int hpWidth = (int) ((float) currentEnemy.hp / currentEnemy.maxHp * barW);
            g2d.fillRect(barX, barY, hpWidth, barH);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(barX, barY, barW, barH);
            g2d.drawString("HP: " + currentEnemy.hp + "/" + currentEnemy.maxHp, barX + 40, barY + 15);
        }
    }

    private void drawUI(Graphics2D g2d, int w, int h, int viewH) {
        if (gameState == GameState.EXPLORING) {
            drawMinimap(g2d);
        }

        // Bottom panel
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, viewH, w, h - viewH);
        g2d.setColor(new Color(80, 80, 80));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRect(10, viewH + 10, w - 20, h - viewH - 20);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2d.drawString("THE FORGOTTEN DEPTHS", 40, viewH + 40);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));

        if (gameState == GameState.EXPLORING) {
            g2d.drawString("Level: " + player.level + " | Gold: " + player.gold + " | Exp: " + player.exp, 40, viewH + 70);
            g2d.drawString("HP: [" + getHPBar() + "] " + player.hp + "/" + player.maxHp, 40, viewH + 95);
            g2d.drawString("Pos: (" + player.x + "," + player.y + ") | Facing: " + player.facing, 40, viewH + 120);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 14));
            g2d.drawString("WASD: Move/Turn | E: Open Chest | R: Rest | SPACE: Interact", 40, viewH + 145);
        } else if (gameState == GameState.COMBAT) {
            g2d.drawString("HP: [" + getHPBar() + "] " + player.hp + "/" + player.maxHp, 40, viewH + 70);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2d.setColor(Color.YELLOW);
            g2d.drawString("COMBAT!", 40, viewH + 100);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g2d.drawString("1/A: Attack  |  2/D: Defend  |  3/R: Run", 40, viewH + 130);
        } else if (gameState == GameState.CHEST_OPEN) {
            g2d.setColor(Color.YELLOW);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2d.drawString("CHEST OPENED!", 40, viewH + 70);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
            if (currentChest != null) {
                g2d.drawString("Found " + currentChest.gold + " gold!", 40, viewH + 95);
                if (currentChest.trapped) {
                    g2d.setColor(Color.RED);
                    g2d.drawString("It was trapped! (" + currentChest.trapType + ")", 40, viewH + 120);
                }
            }
            g2d.setColor(Color.GRAY);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 14));
            g2d.drawString("Press SPACE to continue", 40, viewH + 145);
        } else if (gameState == GameState.TRAP_TRIGGERED) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2d.drawString("TRAP!", 40, viewH + 70);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g2d.drawString("HP: [" + getHPBar() + "] " + player.hp + "/" + player.maxHp, 40, viewH + 95);
            g2d.setColor(Color.GRAY);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 14));
            g2d.drawString("Press SPACE to continue", 40, viewH + 120);
        } else if (gameState == GameState.RESTING) {
            g2d.setColor(Color.CYAN);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2d.drawString("RESTING...", 40, viewH + 70);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g2d.drawString("HP: [" + getHPBar() + "] " + player.hp + "/" + player.maxHp, 40, viewH + 95);
            g2d.setColor(Color.GRAY);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 14));
            if (restAmbushed) {
                g2d.setColor(Color.RED);
                g2d.drawString("Press SPACE to fight!", 40, viewH + 120);
            } else {
                g2d.drawString("Press SPACE to continue", 40, viewH + 120);
            }
        }

        // Draw messages
        if (messageTimer > 0) {
            g2d.setColor(Color.YELLOW);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
            String[] lines = message.split("\n");
            for (int i = 0; i < lines.length; i++) {
                g2d.drawString(lines[i], w / 2 - 200, viewH - 30 - (lines.length - i - 1) * 20);
            }
        }
    }

    private String getHPBar() {
        int bars = (int) ((float) player.hp / player.maxHp * 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i < bars ? "|" : " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Wizardry-Style Dungeon Crawler");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new DungeonDemo());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
