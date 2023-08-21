{
  description = "A Nix-flake-based Java development environment";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";

  outputs = { self, nixpkgs }:
    let


      ########################################
      #                                      #
      #  Change this value to update the     #
      #  whole stack                         #
      #                                      #
      ########################################
      javaVersion = 20;


      overlays = [
        (final: prev: rec {
          jdk = prev."jdk${toString javaVersion}";
          gradle = prev.gradle.override { java = jdk; };
          maven = prev.maven.override { inherit jdk; };
        })
      ];
      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { 
          inherit overlays system; 
          config = {
            allowUnfree = true;  # To allow installing IntelliJ IDEA Ultimate
          };
        };
      });
    in
    {
      devShells = forEachSupportedSystem ({ pkgs }: {
        default = pkgs.mkShell {

          ########################################
          #                                      #
          #  Add system packages here            #
          #                                      #
          ########################################
          packages = with pkgs; [ 

            # Basic Java stuff
            jdk 
            gradle 
            maven 

            # IDE
            jetbrains.idea-ultimate  # or jetbrains.idea-community
            jdt-language-server      # for people who like VSCode, Vim, and Emacs
          ];



        };
      });
    };
}
