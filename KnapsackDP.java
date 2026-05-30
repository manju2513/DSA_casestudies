class KnapsackDP {
    public static void main(String[] args) {
        int[] weight = {2, 3, 4, 5, 7};
        int[] value = {6, 4, 5, 8, 10};
        String[] items = {
                "Electronics",
                "Books",
                "Clothing",
                "Toys",
                "Groceries"
        };
        int n = weight.length;
        int W = 10;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (weight[i - 1] <= w)
                    dp[i][w] = Math.max(
                            value[i - 1]
                                    + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]
                    );
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        System.out.println(
                "Maximum Value = "
                        + dp[n][W]
                        + " (Rs. "
                        + (dp[n][W] * 100)
                        + ")"
        );
        System.out.println("\nSelected Items:");
        int w = W;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println(
                        items[i - 1]
                                + " (Weight = "
                                + weight[i - 1]
                                + ", Value = "
                                + value[i - 1]
                                + ")"
                );
                w = w - weight[i - 1];
            }
        }
    }
}