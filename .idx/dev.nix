# To learn more about how to use Nix to configure your environment
# see: https://developers.google.com/idx/guides/customize-idx-env
{ pkgs, ... }: {
  # Which nixpkgs channel to use.
  channel = "stable-23.11"; # or "unstable"
  services.docker.enable = true;
  services.mysql = {
    enable = true;
    package = pkgs.mariadb;
  };
  # Use https://search.nixos.org/packages to find packages
  packages = [
    pkgs.nodejs_18
    pkgs.jdk17
    pkgs.php
    pkgs.postgresql_15
    pkgs.maven
    pkgs.tree
    pkgs.neofetch
  ];
  # Sets environment variables in the workspace
  env = {};
  idx = {
    # Search for the extensions you want on https://open-vsx.org/ and use "publisher.id"
    extensions = [
      "vscjava.vscode-java-pack"
      "vmware.vscode-spring-boot"
      "vscjava.vscode-java-debug"
      "vscjava.vscode-java-dependency"
      "vscjava.vscode-java-pack"
      "vscjava.vscode-java-test"
      "vscjava.vscode-maven"
      "vscjava.vscode-spring-boot-dashboard"
      "vscjava.vscode-spring-initializr"
      "ckolkman.vscode-postgres"
      "rangav.vscode-thunder-client"
      "VMware.vscode-boot-dev-pack"
      "humao.rest-client"
      "formulahendry.vscode-mysql"
      "GitHub.vscode-github-actions"
    ];
    # Enable previews
    previews = {
    };
    # Workspace lifecycle hooks
    workspace = {
    };
  };
}